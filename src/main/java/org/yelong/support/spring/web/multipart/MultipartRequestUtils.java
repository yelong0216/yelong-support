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
 * 有关文件上传的请求工具类
 * 
 * 可以更方便的获取上传的文件
 * 
 * @author PengFei
 * @since 1.3.0
 */
public final class MultipartRequestUtils {

	private MultipartRequestUtils() {
	}

	// 当前线程的多部分解析器持有者
	private static final ThreadLocal<MultipartRequest> MULTIPART_REQUEST_HOLDER = new ThreadLocal<>();

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
	 * {@link MultipartResolver#isMultipart(HttpServletRequest)} 如果
	 * {@link #MULTIPART_REQUEST_HOLDER}的value值不为 null则直接返回已经存储的值
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
	 * {@link MultipartResolver#isMultipart(HttpServletRequest)} 如果
	 * {@link #MULTIPART_REQUEST_HOLDER}的value值不为 null则直接返回已经存储的值
	 * 
	 * @param request           source request
	 * @param multipartResolver 多部分解析器
	 * @return 多部分的request
	 * @see MultipartResolver
	 * @see MultipartRequest
	 */
	public static MultipartRequest getMultipartRequest(HttpServletRequest request,
			MultipartResolver multipartResolver) {
		MultipartRequest multipartRequest = MULTIPART_REQUEST_HOLDER.get();
		if (multipartRequest != null) {
			return multipartRequest;
		}
		if (!multipartResolver.isMultipart(request)) {
			return null;
		}
		multipartRequest = multipartResolver.resolveMultipart(request);
		MULTIPART_REQUEST_HOLDER.set(multipartRequest);
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
