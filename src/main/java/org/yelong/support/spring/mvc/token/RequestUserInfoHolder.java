/**
 * 
 */
package org.yelong.support.spring.mvc.token;

/**
 * 请求用户信息持有者
 * @author 彭飞
 * @date 2019年8月30日下午3:58:38
 * @version 1.2
 * @see AbstractTokenHandlerInterceptor
 */
public abstract class RequestUserInfoHolder {
	
	
	private RequestUserInfoHolder() {}
	
	private static final ThreadLocal<RequestUserInfo> REQUEST_USER_INFO_HOLDER = 
			new ThreadLocal<>();

	/**
	 * 设置请求用户信息
	 * @author 彭飞
	 * @date 2019年8月30日下午4:10:57
	 * @version 1.2
	 * @param requestUserInfo 请求用户信息
	 */
	public static void setRequestUserInfo(RequestUserInfo requestUserInfo) {
		REQUEST_USER_INFO_HOLDER.set(requestUserInfo);
	}

	/**
	 * 获取当前的请求用户信息<br/>
	 * 如果token没有进行验证，或者没有验证通过，这将返回null
	 * @author 彭飞
	 * @date 2019年8月30日下午4:11:51
	 * @version 1.2
	 * @return 请求用户信息
	 */
	public static RequestUserInfo currentRequestUserInfo() {
		return REQUEST_USER_INFO_HOLDER.get();
	}
	
	
}
