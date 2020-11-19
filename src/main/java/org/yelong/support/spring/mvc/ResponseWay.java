/**
 * 
 */
package org.yelong.support.spring.mvc;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME	;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
/**
 * 处理器方法的响应方式
 * 
 * @since 2.2
 */
public @interface ResponseWay {

	/**
	 * 指定处理器方法的响应方式
	 * 
	 * @return 处理器方法的响应方式
	 */
	HandlerResponseWay value() default HandlerResponseWay.JSON;

}
