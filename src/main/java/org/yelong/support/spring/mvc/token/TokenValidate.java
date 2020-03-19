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
 * @author 彭飞
 * @date 2019年8月30日下午3:08:46
 * @version 1.2
 */
public @interface TokenValidate {

	/**
	 * 请求token的参数名称
	 * @author 彭飞
	 * @date 2019年8月7日上午11:02:59
	 * @version 1.2
	 * @return
	 */
	String tokenParamName() default "token";
	
	/**
	 * token所在位置
	 * 支持多个位置并按照顺序依次尝试去取token
	 * @author 彭飞
	 * @date 2019年8月7日上午11:07:19
	 * @version 1.2
	 * @return
	 */
	TokenLocation [] tokenLocation() default TokenLocation.COOKIE;
	
	
	/**
	 * 是否开启token验证
	 * @author 彭飞
	 * @date 2019年8月18日下午1:18:32
	 * @version 1.2
	 * @return
	 */
	boolean validate() default true;
	
	
	/**
	 * token位置
	 * @author 彭飞
	 * @date 2019年8月18日下午1:16:21
	 * @version 1.2
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
