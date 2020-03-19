/**
 * 
 */
package org.yelong.support.properties.wired;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ TYPE })
/**
 * @author 彭飞
 * @date 2019年9月11日上午9:26:36
 * @version 1.2
 */
public @interface PropertiesWired {

	String propertiesName() default "";
	
	String prefix() default "";
	
}
