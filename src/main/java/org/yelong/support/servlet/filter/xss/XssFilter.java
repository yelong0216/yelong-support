/**
 * 
 */
package org.yelong.support.servlet.filter.xss;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * xss攻击过滤
 * @author 彭飞
 * @date 2019年9月17日下午12:02:57
 * @version 1.2
 */
public class XssFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(req);
		chain.doFilter(xssRequest, response);
	}

}
