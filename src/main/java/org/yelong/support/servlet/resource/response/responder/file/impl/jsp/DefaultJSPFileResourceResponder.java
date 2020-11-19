package org.yelong.support.servlet.resource.response.responder.file.impl.jsp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.yelong.commons.io.IOUtilsE;
import org.yelong.commons.lang.StringUtilsE;
import org.yelong.commons.util.MapUtilsE;
import org.yelong.commons.util.PlaceholderUtils;
import org.yelong.core.resource.ScopeResourceSupplierManager;
import org.yelong.support.resource.freemarker.TemplateResourceHandler;
import org.yelong.support.resource.web.WebResourceSupplier;
import org.yelong.support.servlet.resource.response.ResourceResponseException;
import org.yelong.support.servlet.resource.response.ResourceResponseProperties;
import org.yelong.support.servlet.resource.response.responder.file.AbstractTemplateFileResourceResponder;
import org.yelong.support.servlet.resource.response.responder.file.FileResourceResponseUtils;
import org.yelong.support.servlet.resource.response.responder.file.TemplateFileResourceResponder;

import freemarker.template.TemplateException;

/**
 * JSP文件资源响应器。支持 <jsp:include page="/common/commonlib.jsp"></jsp:include>
 * 标签来引入其他的资源
 * 
 * @since 2.2
 */
public class DefaultJSPFileResourceResponder extends AbstractTemplateFileResourceResponder
		implements JSPFileResourceResponder {

	private ScopeResourceSupplierManager scopeResourceSupplierManager;

	private WebResourceSupplier webResourceSupplier;

	public DefaultJSPFileResourceResponder(ScopeResourceSupplierManager scopeResourceSupplierManager,
			WebResourceSupplier webResourceSupplier, TemplateResourceHandler templateResourceHandler) {
		super(templateResourceHandler);
		this.scopeResourceSupplierManager = scopeResourceSupplierManager;
		this.webResourceSupplier = webResourceSupplier;
		addSupportResourceTypes("jsp");
	}

	@Override
	public void response(InputStream resourceInputStream, ResourceResponseProperties resourceResponseProperties)
			throws ResourceResponseException, IOException {
		HttpServletResponse response = resourceResponseProperties.getResponse();
		response.setContentType(jspContentType);
		FileResourceResponseUtils.responseText(() -> {
			String text = IOUtilsE.readString(resourceInputStream);
			IOUtilsE.closeQuietly(resourceInputStream);
			JSPProcess process = new JSPProcess(text, resourceResponseProperties);
			try {
				return process.process();
			} catch (TemplateException e) {
				throw new ResourceResponseException(e);
			}
		}, resourceResponseProperties);

	}

	/**
	 * JSP解析
	 */
	public class JSPProcess {

		public static final String JSP_INCLUDE_PATTERN = "\\<jsp:include page=\"(.*?)\"></jsp:include\\>";

		private final String jspContent;

		private final ResourceResponseProperties resourceResponseProperties;

		private final Map<String, Object> templateProcessAfterParams;

		public JSPProcess(String jspContent, ResourceResponseProperties resourceResponseProperties) {
			this.jspContent = jspContent;
			this.resourceResponseProperties = resourceResponseProperties;
			Map<String, Object> params = MapUtilsE.asMap(
					TemplateFileResourceResponder.resourceResponsePropertiesParamName, resourceResponseProperties);
			templateProcessAfterParams = templateResourceHandler.processTemplateParameter(params);
		}

		/**
		 * 加工JSP代码
		 * 
		 * @return 加工后的JSP代码
		 */
		public String process() throws IOException, TemplateException {
			// 需要将jsp:include的值转换。如果是${}需要转换
			String jspContent = parseJspIncludeTag();
			templateResourceHandler.setNextHandleWhetherProcessParam(false);
			String text = templateResourceHandler.handle(jspContent, templateProcessAfterParams);
			return text;
		}

		/**
		 * 解析 jsp:include 标签
		 */
		protected String parseJspIncludeTag() throws IOException {
			String jspContent = this.jspContent;
			Pattern p = Pattern.compile(JSP_INCLUDE_PATTERN);
			Matcher m = p.matcher(jspContent);
			while (m.find()) { // 当字符串中有匹配到 规则 时
				String param = m.group(0); // 规则 和里面的内容
				String resourcePath = getJspIncludePageValue(param);

				// 是否是占位符。如果是占位符则获取占位符中的值，并用模板处理后的参数进行替换
				String value = PlaceholderUtils.getDollarBraceContent(resourcePath);
				if (StringUtils.isNotBlank(value)) {
					String paramValue = (String) templateProcessAfterParams.get(value);
					if (null != paramValue) {
						resourcePath = paramValue;
					}
				}

				String jspIncludePageCode = null;
				ServletContext servletContext = resourceResponseProperties.getRequest().getServletContext();
				InputStream inputStream = webResourceSupplier.getResourceAsStream(servletContext, resourcePath);
				if (null == inputStream) {
					inputStream = scopeResourceSupplierManager.getResourceAsStream(resourcePath);
				}
				if (null == inputStream) {
					jspIncludePageCode = "<!--资源不存在" + resourcePath + "-->";
				} else {
					jspIncludePageCode = IOUtilsE.readString(inputStream);
					IOUtilsE.closeQuietly(inputStream);
				}
				jspContent = StringUtilsE.replaceFirst(jspContent, param, jspIncludePageCode);
			}
			return jspContent;
		}

		/**
		 * 获取 jsp:include 标签中 page 属性的值
		 * 
		 * @param jspIncludeCode jsp:include 标签
		 * @return jsp:include 标签中page属性的值
		 */
		public String getJspIncludePageValue(String jspIncludeCode) {
			return jspIncludeCode.substring("<jsp:include page=\"".length(),
					jspIncludeCode.length() - "\"></jsp:include>".length());
		}

	}

}
