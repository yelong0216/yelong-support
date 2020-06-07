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
 * 
 * @author PengFei
 */
public @interface RecordLog {

	/**
	 * 是否记录日志
	 * 
	 * @return <tt>true</tt> 记录日志
	 */
	boolean isRecordLog() default true;
	
	/**
	 * @return 操作备注
	 */
	String remark() default "";
	
}
