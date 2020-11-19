/**
 * 
 */
package org.yelong.support.spring.web.multipart;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.yelong.support.spring.ApplicationContextDecorator;

/**
 * 有关文件上传的请求工具类。可以更方便的获取上传的文件<br/>
 * 在 2.1.3 版本中取消了线程变量存储
 * {@link MultipartRequest}对象，改为直接解析。因为线程存储有BUG，会导致文件上传了但是找不到
 * 
 * @version 2.1.3
 * @since 1.3
 */
public final class MultipartRequestUtils {

	private MultipartRequestUtils() {
	}

	/**
	 * 获取Spring上下文内的 MultipartResolver
	 * 
	 * @return 多部分解析器
	 * @see ApplicationContextDecorator
	 */
	public static MultipartResolver getMultipartResolver() {
		return ApplicationContextDecorator.getBean(MultipartResolver.class);
	}

	/**
	 * 将 request 解析为多部分的 request。 如果这个 request 不能被解析则返回 null 。
	 * 
	 * @param request source request
	 * @return 多部分的request
	 * @see MultipartResolver
	 * @see MultipartRequest
	 */
	public static MultipartRequest getMultipartRequest(HttpServletRequest request) {
		return getMultipartRequest(request, getMultipartResolver());
	}

	/**
	 * 将 request 解析为多部分的 request。 如果这个 request 不能被解析则返回 null 。
	 * 
	 * @param request           source request
	 * @param multipartResolver 多部分解析器
	 * @return 多部分的request
	 * @see MultipartResolver
	 * @see MultipartRequest
	 */
	public static MultipartRequest getMultipartRequest(HttpServletRequest request,
			MultipartResolver multipartResolver) {
		if (!multipartResolver.isMultipart(request)) {
			return null;
		}
		MultipartRequest multipartRequest = multipartResolver.resolveMultipart(request);
		return multipartRequest;
	}

	/**
	 * 获取请求中上传的文件。 如果无法解析 request 为 {@link MultipartRequest}则返回null
	 * 
	 * @param request request
	 * @param name    param name
	 * @return 上传的文件信息
	 * @see MultipartResolver
	 * @see MultipartRequest
	 * @see MultipartFile
	 */
	public static MultipartFile getMultipartFile(HttpServletRequest request, String name) {
		MultipartRequest multipartRequest = getMultipartRequest(request);
		if (null == multipartRequest) {
			return null;
		}
		return multipartRequest.getFile(name);
	}

}
