/**
 * 
 */
package org.yelong.support.spring.mvc.token;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({METHOD,TYPE})
/**
 * token验证<br/>
 * 被标注的请求方法根据指定的规则验证token<br/>
 * 验证通过后，可以通过RequetUserInfoHolder来获取当前请求的用户信息
 * 
 * @author PengFei
 */
public @interface TokenValidate {

	/**
	 * 请求token的参数名称
	 * 
	 * @return token的参数名称
	 */
	String tokenParamName() default "token";
	
	/**
	 * token所在位置
	 * 支持多个位置并按照顺序依次尝试去取token
	 * 
	 * @return token的存放位置
	 */
	TokenLocation [] tokenLocation() default TokenLocation.COOKIE;
	
	/**
	 * 是否开启token验证
	 * 
	 * @return <code>true</code> 验证token
	 */
	boolean validate() default true;
	
	/**
	 * token位置
	 */
	public static enum TokenLocation {

		/**请求头*/
		HEADER,
		/**请求参数*/
		PARAMER,
		/**Cookie*/
		COOKIE
	}
	
}
