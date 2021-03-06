/**
 * 
 */
package org.yelong.support.spring.mvc.exception;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.yelong.support.spring.mvc.HandlerResponseWay;

/**
 * 根据响应结果方式来响应异常信息<br/>
 * 
 * @since 1.0
 * @see AbstractHandlerMethodExceptionResolverByResponseWay
 */
public abstract class AbstractHandlerExceptionResolverByResponseWay extends AbstractHandlerExceptionResolver {

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		ModelAndView modelAndView = null;
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
			HandlerResponseWay handlerResponseWay = HandlerResponseWay.handlerResponseWayResolver(handlerMethod);
			if (handlerResponseWay == HandlerResponseWay.JSON) {
				response.setStatus(HttpStatus.OK.value()); // 设置状态码
				response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE); // 设置ContentType
				response.setCharacterEncoding("UTF-8"); // 避免乱码
				response.setHeader("Cache-Control", "no-cache, must-revalidate");
				String json = handlerExceptionResponseJson(request, response, handlerResponseWay, ex);
				try {
					response.getWriter().write(json);
				} catch (IOException e) {
					logger.error("处理器异常解析器响应JSON异常", e);
				}
				modelAndView = new ModelAndView();
			} else if (handlerResponseWay == HandlerResponseWay.MODEL_AND_VIEW) {
				modelAndView = handlerExceptionResponseModelAndView(request, response, handlerResponseWay, ex);
			}
		}
		return modelAndView;
	}

	/**
	 * 异常信息响应返回json格式的信息
	 * 
	 * @return json
	 */
	protected abstract String handlerExceptionResponseJson(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex);

	/**
	 * 异常信息响应返回视图
	 * 
	 * @return 视图
	 */
	protected abstract ModelAndView handlerExceptionResponseModelAndView(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex);

}
