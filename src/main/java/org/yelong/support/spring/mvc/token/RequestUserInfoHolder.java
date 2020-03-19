/**
 * 
 */
package org.yelong.support.spring.mvc.token;

/**
 * 请求用户信息持有者
 * @author PengFei
 * @see AbstractTokenHandlerInterceptor
 */
public abstract class RequestUserInfoHolder {
	
	private RequestUserInfoHolder() {}
	
	private static final ThreadLocal<RequestUserInfo> REQUEST_USER_INFO_HOLDER = 
			new ThreadLocal<>();

	/**
	 * 设置请求用户信息
	 * @param requestUserInfo 请求用户信息
	 */
	public static void setRequestUserInfo(RequestUserInfo requestUserInfo) {
		REQUEST_USER_INFO_HOLDER.set(requestUserInfo);
	}

	/**
	 * 获取当前的请求用户信息<br/>
	 * 如果token没有进行验证，或者没有验证通过，这将返回null
	 * @return 请求用户信息
	 */
	public static RequestUserInfo currentRequestUserInfo() {
		return REQUEST_USER_INFO_HOLDER.get();
	}
	
}
