/**
 * 
 */
package org.yelong.support.spring.mvc.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.yelong.core.annotation.Nullable;
import org.yelong.support.servlet.CookieUtils;
import org.yelong.support.spring.mvc.interceptor.AbstractHandlerInterceptor;
import org.yelong.support.spring.mvc.token.TokenValidate.TokenLocation;

/**
 * 抽象token拦截器实现
 * 
 * @since 1.0
 */
public abstract class AbstractTokenHandlerInterceptor extends AbstractHandlerInterceptor {

	/**
	 * 是否保存用户信息 默认为true token认证通过后将用户信息保存在{@link RequestUserInfoHolder}中
	 */
	private boolean saveRequesteUserInfo = true;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			TokenValidate tokenValidate = getTokenValidate(handlerMethod);
			// 不需要token验证
			if (!isValidateToken(request, handlerMethod, tokenValidate)) {
				return true;
			}
			String token = getToken(request, tokenValidate);
			boolean validateToken = false;
			InvalidTokenException invalidTokenException = null;
			try {
				validateToken = validateToken(token);
				if (!validateToken) {
					throw new InvalidTokenException(token);
				}
			} catch (InvalidTokenException e) {
				invalidTokenException = e;
				validateToken = false;
			}
			if (validateToken) {
				if (saveRequesteUserInfo)
					RequestUserInfoHolder
							.setRequestUserInfo(validTokenHandler(request, response, handlerMethod, token));
				return true;
			} else {
				invalidTokenHandler(request, response, invalidTokenException);
				return false;
			}
		}
		return true;
	}

	/**
	 * 是否验证token
	 * 
	 * @return <code>true</code> 验证token
	 */
	protected boolean isValidateToken(HttpServletRequest request, HandlerMethod handlerMethod,
			@Nullable TokenValidate tokenValidate) {
		// 不存在 TokenValidate注解默认不需要token验证
		if (null == tokenValidate || !tokenValidate.validate()) {
			return false;
		}
		return true;
	}

	/**
	 * 验证token<br/>
	 * 
	 * @param token 请求中的token
	 * @return <tt>true</tt> 这是一个有效的token
	 * @throws InvalidTokenException 无效的token
	 */
	protected abstract boolean validateToken(String token) throws InvalidTokenException;

	/**
	 * 有效的token处理<br/>
	 * 只有当token是有效且 saveRequesteUserInfo = true 时才会被调用<br/>
	 * 初始化该请求用户的信息<br/>
	 * 
	 * @param request  请求
	 * @param response 响应
	 * @param handler  处理器
	 * @param token    请求的token
	 * @return 当前请求的用户信息
	 */
	protected RequestUserInfo validTokenHandler(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod hanlderMethod, String token) throws Exception {
		return null;
	}

	/**
	 * 只有当token是无效时才会被调用<br/>
	 * 处理无效token的响应结果
	 * 
	 * @param request               请求
	 * @param response              响应
	 * @param invalidTokenException 无效token异常信息
	 */
	protected void invalidTokenHandler(HttpServletRequest request, HttpServletResponse response,
			InvalidTokenException invalidTokenException) throws Exception {

	}

	/**
	 * 根据处理器方法、处理器方法类、处理器方法类的父类直至Object.class中获取TokenValidate<br/>
	 * 如果没有TokenValidate则返回null
	 * 
	 * @param handlerMethod 处理器方法
	 * @return TokenValidate
	 */
	protected TokenValidate getTokenValidate(HandlerMethod hanlderMethod) {
		return getHandlerMethodAnnotation(hanlderMethod, TokenValidate.class);
	}

	/**
	 * 获取请求中的token
	 * 
	 * @param request       request
	 * @param tokenValidate tokenValidate
	 * @return token 值
	 */
	protected String getToken(HttpServletRequest request, TokenValidate tokenValidate) {
		return getToken(request, tokenValidate.tokenLocation(), tokenValidate.tokenParamName());
	}

	/**
	 * 获取token
	 * 
	 * @param request        request
	 * @param tokenLocation  token 存放的未知
	 * @param tokenParamName token的名称
	 * @return token值
	 */
	protected String getToken(HttpServletRequest request, TokenLocation[] tokenLocations, String tokenParamName) {
		if (ArrayUtils.isEmpty(tokenLocations)) {
			return null;
		}
		String tokenValue = "";
		for (TokenLocation tokenLocation : tokenLocations) {
			if (tokenLocation == TokenLocation.HEADER) {
				tokenValue = request.getHeader(tokenParamName);
			} else if (tokenLocation == TokenLocation.PARAMER) {
				tokenValue = request.getParameter(tokenParamName);
			} else if (tokenLocation == TokenLocation.COOKIE) {
				tokenValue = CookieUtils.getCookie(request, tokenParamName);
			}
			if (StringUtils.isNotEmpty(tokenValue)) {
				break;
			}
		}
		return tokenValue;
	}

	public void setSaveRequesteUserInfo(boolean saveRequesteUserInfo) {
		this.saveRequesteUserInfo = saveRequesteUserInfo;
	}

	public boolean isSaveRequesteUserInfo() {
		return saveRequesteUserInfo;
	}

}
