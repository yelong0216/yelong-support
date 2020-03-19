/**
 * 
 */
package org.yelong.support.servlet.filter.xss;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.yelong.support.servlet.wrapper.HttpServletRequestReuseWrapper;

/**
 * Xss request包装器
 * @author 彭飞
 * @date 2019年9月17日下午12:06:59
 * @version 1.2
 */
public class XssHttpServletRequestWrapper extends HttpServletRequestReuseWrapper{

	private HttpServletRequest orgRequest = null;

	public XssHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		this.orgRequest = request;
	}

	@Override
	public String getParameter(String name) {
		String value = super.getParameter(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}

	@Override
	public Map<String, String[]> getParameterMap() {
		Map<String, String[]> paramMap = new HashMap<>(super.getParameterMap());
		for (Map.Entry<String, String[]> entry : paramMap.entrySet()) {
			entry.setValue(xssEncode((String[])entry.getValue()));
		}
		return paramMap;
	}

	@Override
	public String getHeader(String name) {
		String value = super.getHeader(xssEncode(name));
		if (value != null) {
			value = xssEncode(value);
		}
		return value;
	}
	/**
	 * 
	 * @author 彭飞
	 * @date 2019年9月17日下午12:09:54
	 * @version 1.2
	 * @param ss
	 * @return
	 */
	private static String[] xssEncode(String[] ss) {
		if (ss == null) 
			return ss;
		for (int i = 0; i < ss.length; i++) {
			ss[i] = xssEncode(ss[i]);
		}
		return ss;
	}

	/**
	 * 
	 * @author 彭飞
	 * @date 2019年9月17日下午12:09:24
	 * @version 1.2
	 * @param s
	 * @return
	 */
	private static String xssEncode(String s) {
		if ((s == null) || (s.isEmpty())) {
			return s;
		}
		StringBuilder sb = new StringBuilder(s.length() + 16);
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case '>':
				sb.append("&gt;");
				break;
			case '<':
				sb.append("&lt;");
				break;
			case '\\':
				sb.append(65340);
				break;
			case '#':
				sb.append(65283);
				break;
			default:
				sb.append(c);
			}
		}
		return sb.toString();
	}

	public HttpServletRequest getOrgRequest() {
		return this.orgRequest;
	}

}
