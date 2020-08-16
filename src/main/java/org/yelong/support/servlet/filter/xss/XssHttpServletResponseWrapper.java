/**
 * 
 */
package org.yelong.support.servlet.filter.xss;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.wrapper.HttpServletResponseReuseWrapper;

/**
 * @since 1.0
 */
public class XssHttpServletResponseWrapper extends HttpServletResponseReuseWrapper {

	public XssHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public String encodeURL(String url) {
		String result = url != null ? url.toString() : "";
		try {
			result = URLEncoder.encode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
