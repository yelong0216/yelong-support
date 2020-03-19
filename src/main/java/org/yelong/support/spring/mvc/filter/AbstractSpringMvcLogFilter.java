/**
 * 
 */
package org.yelong.support.spring.mvc.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.yelong.support.servlet.filter.log.AbstractLogFilter;
import org.yelong.support.servlet.filter.log.HttpServletLogInfo;

/**
 * spring mvc 日志过滤器
 * @author 彭飞
 * @date 2019年9月19日下午5:51:36
 * @version 1.2
 */
public abstract class AbstractSpringMvcLogFilter extends AbstractLogFilter{


	@Override
	protected void recordLog(HttpServletLogInfo logInfo, HttpServletRequest request, HttpServletResponse response) {
		
	}
	

}
