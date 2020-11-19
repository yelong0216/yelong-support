package org.yelong.support.servlet.resource.response.responder.file;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.yelong.commons.util.Dates;

/**
 * 304状态标识文件未作修改，浏览器可以直接从浏览器缓存中获取
 * 
 * @since 2.2
 */
public class Status304 {

	public static final String lastModified = Dates.nowDate().toString();

	public static final int status = 304;

	/**
	 * @return <code>true</code> 响应304
	 */
	public static boolean handle(HttpServletRequest request, HttpServletResponse response) {
		String ifModifiedSince = request.getHeader("If-Modified-Since");
		response.setHeader("Last-Modified", lastModified);
		response.setHeader("Date", lastModified);
		if (StringUtils.isBlank(ifModifiedSince)) {
			return false;
		}
		// If-Modified-Since 如果If-Modified-Since与Last-Modified相同 则响应 304
		if (ifModifiedSince.equals(lastModified)) {
			response.setStatus(status);
			return true;
		}
		return false;
	}

}
