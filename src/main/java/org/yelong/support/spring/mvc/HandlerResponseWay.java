/**
 * 
 */
package org.yelong.support.spring.mvc;

import java.lang.reflect.Method;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;

/**
 * 处理器响应方式
 * @author 彭飞
 * @date 2019年8月18日下午12:19:03
 * @version 1.0
 */
public enum HandlerResponseWay {
	
	/**json格式*/
	JSON,
	/**视图*/
	MODEL_AND_VIEW;
	
	/**
	 * 解析处理器响应方式<br/>
	 * 满足一下条件视为JSON格式。非JSON均视为视图<br/>
	 * 1、处理器方法存在{@link ResponseBody}注解
	 * 2、处理器方法类上存在{@link ResponseBody}注解
	 * 3、响应结果为{@link ResponseEntity}
	 * @author 彭飞
	 * @date 2019年8月18日下午12:20:52
	 * @version 1.0
	 * @param handlerMethod 处理器方法
	 * @return 处理器方法的相应方式
	 */
	public static HandlerResponseWay handlerResponseWayResolver(HandlerMethod handlerMethod) {
		Method method = handlerMethod.getMethod();
		HandlerResponseWay handlerResponseWay = null;
		if(method.isAnnotationPresent(ResponseBody.class)) {
			handlerResponseWay = JSON;
		} else if( method.getDeclaringClass().isAnnotationPresent(ResponseBody.class)) {
			handlerResponseWay =  JSON;
		} else if( method.getDeclaringClass().isAnnotationPresent(RestController.class)) {
			handlerResponseWay =  JSON;
		} else if( method.getReturnType() == ResponseEntity.class){
			handlerResponseWay =  JSON;;
		} else {
			handlerResponseWay =  MODEL_AND_VIEW;
		}
		return handlerResponseWay;
	}
	
	
}
