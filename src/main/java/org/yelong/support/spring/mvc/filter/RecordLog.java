/**
 * 
 */
package org.yelong.support.spring.mvc.filter;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
/**
 * 记录日志
 * @author 彭飞
 * @date 2019年9月19日下午5:52:43
 * @version 1.2
 */
public @interface RecordLog {

	/**
	 * 是否记录日志
	 * @author 彭飞
	 * @date 2019年9月19日下午6:21:22
	 * @version 1.2
	 * @return <tt>true</tt> 记录日志
	 */
	boolean isRecordLog() default true;
	
	/**
	 * 操作备注
	 * @author 彭飞
	 * @date 2019年9月19日下午6:24:24
	 * @version 1.2
	 * @return
	 */
	String remark() default "";
	
	
}
