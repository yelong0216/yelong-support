/**
 * 
 */
package org.yelong.support.servlet.filter.xss;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.wrapper.HttpServletResponseReuseWrapper;

/**
 * @author 彭飞
 * @date 2019年9月17日下午12:11:44
 * @version 1.2
 */
public class XssHttpServletResponseWrapper extends HttpServletResponseReuseWrapper{
	
	public XssHttpServletResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	public String encodeURL(String url){
		String result = url != null ? url.toString() : "";
		try {
			result = URLEncoder.encode(result, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

}
