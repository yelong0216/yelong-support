/**
 * 
 */
package org.yelong.support.spring.mvc;

import java.lang.reflect.Method;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.yelong.commons.lang.annotation.AnnotationUtilsE;

/**
 * 处理器响应方式
 * 
 * @since 1.0
 */
public enum HandlerResponseWay {

	/** JSON格式 */
	JSON,
	/** 视图 */
	MODEL_AND_VIEW;

	/**
	 * <pre>
	 * 解析处理器响应方式
	 * 满足以下条件视为JSON格式。非JSON均视为视图
	 * 1、指定 {@link ResponseWay}注解
	 * 2、处理器方法存在{@link ResponseBody}注解
	 * 3、处理器方法类上存在{@link ResponseBody}注解
	 * 4、响应结果为{@link ResponseEntity}
	 * </pre>
	 * 
	 * @param handlerMethod 处理器方法
	 * @return 处理器方法的相应方式
	 */
	public static HandlerResponseWay handlerResponseWayResolver(HandlerMethod handlerMethod) {
		Method method = handlerMethod.getMethod();
		ResponseWay responseWay = AnnotationUtilsE.getAnnotation(method, ResponseWay.class);
		if (null != responseWay) {
			return responseWay.value();
		}
		if (method.isAnnotationPresent(ResponseBody.class)) {
			return JSON;
		}
		Class<?> declaringClass = method.getDeclaringClass();
		if (declaringClass.isAnnotationPresent(ResponseBody.class)) {
			return JSON;
		}
		if (declaringClass.isAnnotationPresent(RestController.class)) {
			return JSON;
		}
		if (method.getReturnType() == ResponseEntity.class) {
			return JSON;
		}
		return MODEL_AND_VIEW;
	}

}
