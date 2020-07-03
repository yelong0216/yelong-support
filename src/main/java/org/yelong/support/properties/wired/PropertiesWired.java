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
 * @author PengFei
 */
public @interface PropertiesWired {

	String propertiesName() default "";

	String prefix() default "";

}
