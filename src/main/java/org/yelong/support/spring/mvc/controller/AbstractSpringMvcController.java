/**
 * 
 */
package org.yelong.support.spring.mvc.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;
import org.yelong.support.servlet.mvc.AbstractController;
import org.yelong.support.spring.mvc.SpringMvcUtils;
import org.yelong.support.spring.web.multipart.MultipartRequestUtils;

/**
 * 抽象Spring Mvc 控制器 <br/>
 * 
 * 提供一些SpringMvc中常用功能性的方法
 * 
 * @author PengFei
 */
public abstract class AbstractSpringMvcController extends AbstractController {

	@Resource
	private HttpServletResponse response;

	@Override
	public HttpServletRequest getRequest() {
		return SpringMvcUtils.getRequest();
	}

	@Override
	public HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * 获取上传的文件
	 * 
	 * @param name param name
	 * @return 上传的文件信息
	 * @see MultipartRequestUtils#getMultipartFile(HttpServletRequest, String)
	 * @see #getRequest()
	 */
	public MultipartFile getMultipartFile(String name) {
		return MultipartRequestUtils.getMultipartFile(getRequest(), name);
	}

	/**
	 * @return 多部分的request
	 * @see MultipartRequestUtils#getMultipartRequest(HttpServletRequest)
	 * @see #getRequest()
	 */
	public MultipartRequest getMultipartRequest() {
		return MultipartRequestUtils.getMultipartRequest(getRequest());
	}

}
