/**
 * 
 */
package org.yelong.support.servlet;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author PengFei
 */
public final class CookieUtils {

	private CookieUtils() {
	}

	/**
	 * 获取cookie
	 * 
	 * @param request request
	 * @param name    cookie name
	 * @return cookie value
	 */
	public static final String getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

	/**
	 * 添加cookie
	 * 
	 * @param response response
	 * @param name     cookie name
	 * @param value    cookie value
	 */
	public static final void addCookie(HttpServletResponse response, String name, String value) {
		response.addCookie(new Cookie(name, value));
	}

	/**
	 * 移除 cookie
	 * 
	 * @param response 响应
	 * @param name     cookie name
	 */
	public static final void removeCookie(HttpServletResponse response, String name) {
		Cookie newCookie = new Cookie(name, null); // 假如要删除名称为username的Cookie
		newCookie.setMaxAge(0); // 立即删除型
		newCookie.setPath("/"); // 项目所有目录均有效，这句很关键，否则不敢保证删除
		response.addCookie(newCookie); // 重新写入，将覆盖之前的
	}

	/**
	 * 清空cookie 将request中的所有cookie清除
	 * 
	 * @param request  request
	 * @param response response
	 */
	public static final void clearCookie(HttpServletRequest request, HttpServletResponse response) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			removeCookie(response, cookie.getName());
		}
	}

}
