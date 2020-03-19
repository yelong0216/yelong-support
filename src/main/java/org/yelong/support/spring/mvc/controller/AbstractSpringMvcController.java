/**
 * 
 */
package org.yelong.support.spring.mvc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.yelong.support.servlet.mvc.AbstractController;

/**
 * @author PengFei
 */
public abstract class AbstractSpringMvcController extends AbstractController{

	@Resource
	private HttpServletResponse response;
	
	@Override
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
	}
	
	@Override
	public HttpServletResponse getResponse() {
		return response;
	}
	
}
