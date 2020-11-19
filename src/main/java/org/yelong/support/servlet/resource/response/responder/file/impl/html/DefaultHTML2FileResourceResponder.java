package org.yelong.support.servlet.resource.response.responder.file.impl.html;

import org.yelong.core.resource.ScopeResourceSupplierManager;
import org.yelong.support.resource.freemarker.TemplateResourceHandler;
import org.yelong.support.resource.web.WebResourceSupplier;
import org.yelong.support.servlet.resource.response.responder.file.impl.jsp.DefaultJSPFileResourceResponder;

/**
 * 支持模板的html资源代码
 * 
 * @since 2.2
 */
public class DefaultHTML2FileResourceResponder extends DefaultJSPFileResourceResponder
		implements HTML2FileResourceResponder {

	public DefaultHTML2FileResourceResponder(ScopeResourceSupplierManager scopeResourceSupplierManager,
			WebResourceSupplier webResourceSupplier, TemplateResourceHandler templateResourceHandler) {
		super(scopeResourceSupplierManager, webResourceSupplier, templateResourceHandler);
		setSupportResourceTypes(htmlFileType);
	}

}
