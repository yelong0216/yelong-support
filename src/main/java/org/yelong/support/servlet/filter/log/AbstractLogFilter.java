/**
 * 
 */
package org.yelong.support.servlet.filter.log;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.wrapper.HttpServletRequestReuseWrapper;
import org.yelong.support.servlet.wrapper.HttpServletResponseReuseWrapper;

/**
 * 日志过滤器
 * @author 彭飞
 * @date 2019年9月19日下午3:23:05
 * @version 1.2
 */
public abstract class AbstractLogFilter implements Filter{

	@Override
	public final void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//不进行验证
		if(!isRecordLog((HttpServletRequest) request)) {
			chain.doFilter(request, response);
			return;
		}
		HttpServletRequestReuseWrapper requestWrapper = new HttpServletRequestReuseWrapper((HttpServletRequest) request);
		HttpServletResponseReuseWrapper responseWrapper = new HttpServletResponseReuseWrapper((HttpServletResponse)response);
		//记录日志
		HttpServletLogInfo logInfo = new HttpServletLogInfo();
		logInfo.setStartTime(new Date());
		logInfo.setRequestBody(requestWrapper.getBody());
		chain.doFilter(requestWrapper, responseWrapper);
		logInfo.setEndTime(new Date());
		logInfo.setResponseResult(responseWrapper.getContent());
		logInfo.setRequestParams(request.getParameterMap());
		recordLog(logInfo,(HttpServletRequest) request, (HttpServletResponse) response);
	}

	/**
	 * 是否需要记录日志
	 * @author 彭飞
	 * @date 2019年9月19日下午3:25:29
	 * @version 1.2
	 * @param request
	 * @return <tt>true</tt>表示当前请求会记录日志
	 */
	protected abstract boolean isRecordLog(HttpServletRequest request);
	
	/**
	 * 记录日志
	 * 只有当 {@link #isRecordLog(HttpServletRequest)}返回true时才会被调用
	 * @author 彭飞
	 * @date 2019年9月19日下午3:24:48
	 * @version 1.2
	 * @param logInfo 日志信息
	 * @param request
	 * @param response
	 */
	protected abstract void recordLog( HttpServletLogInfo logInfo, HttpServletRequest request, HttpServletResponse response);
	
	
	
}
