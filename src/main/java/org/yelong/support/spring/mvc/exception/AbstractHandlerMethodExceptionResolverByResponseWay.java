package org.yelong.support.spring.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerMethodExceptionResolver;
import org.yelong.core.annotation.Nullable;
import org.yelong.support.spring.mvc.HandlerResponseWay;

/**
 * 根据响应结果方式来响应异常信息
 * 
 * @since 2.2
 */
public abstract class AbstractHandlerMethodExceptionResolverByResponseWay
		extends AbstractHandlerMethodExceptionResolver {

	protected JSONResponseExceptionResolver jsonResponseExceptionResolver;

	protected ViewResponseExceptionResolver viewResponseExceptionResolver;

	public AbstractHandlerMethodExceptionResolverByResponseWay(
			JSONResponseExceptionResolver jsonResponseExceptionResolver,
			ViewResponseExceptionResolver viewResponseExceptionResolver) {
		this.jsonResponseExceptionResolver = jsonResponseExceptionResolver;
		this.viewResponseExceptionResolver = viewResponseExceptionResolver;
	}

	@Override
	protected ModelAndView doResolveHandlerMethodException(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception ex) {
		HandlerResponseWay handlerResponseWay = deduceHandlerResponseWay(handlerMethod);
		if (null == handlerResponseWay) {
			return indeterminationHandlerWayExceptionResolve(request, response, handlerMethod, ex);
		}
		switch (handlerResponseWay) {
		case JSON:
			return jsonHandlerWayExceptionResolve(request, response, handlerMethod, ex);
		case MODEL_AND_VIEW:
			return viewHandlerWayExceptionResolve(request, response, handlerMethod, ex);
		}
		return indeterminationHandlerWayExceptionResolve(request, response, handlerMethod, ex);
	}

	/**
	 * JSON处理方式的异常解析
	 * 
	 * @param request       当前HTTP请求
	 * @param response      当前的HTTP响应
	 * @param handlerMethod 执行的处理程序，或{@code null}(如果在异常时没有选择)(例如，如果多部分解析失败)
	 * @param ex            在处理程序执行期间抛出的异常
	 * @return 要转发的对应的{@code ModelAndView}，或用于解析链中默认处理的{@code null}
	 */
	protected ModelAndView jsonHandlerWayExceptionResolve(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception ex) {
		return jsonResponseExceptionResolver.resolveException(request, response, handlerMethod, ex);
	}

	/**
	 * 视图处理方式的异常解析
	 * 
	 * @param request       当前HTTP请求
	 * @param response      当前的HTTP响应
	 * @param handlerMethod 执行的处理程序，或{@code null}(如果在异常时没有选择)(例如，如果多部分解析失败)
	 * @param ex            在处理程序执行期间抛出的异常
	 * @return 要转发的对应的{@code ModelAndView}，或用于解析链中默认处理的{@code null}
	 */
	protected ModelAndView viewHandlerWayExceptionResolve(HttpServletRequest request, HttpServletResponse response,
			HandlerMethod handlerMethod, Exception ex) {
		return viewResponseExceptionResolver.resolveException(request, response, handlerMethod, ex);
	}

	/**
	 * 
	 * 不确定处理方式的异常解析
	 * 
	 * @param request       当前HTTP请求
	 * @param response      当前的HTTP响应
	 * @param handlerMethod 执行的处理程序，或{@code null}(如果在异常时没有选择)(例如，如果多部分解析失败)
	 * @param ex            在处理程序执行期间抛出的异常
	 * @return 要转发的对应的{@code ModelAndView}，或用于解析链中默认处理的{@code null}
	 */
	protected abstract ModelAndView indeterminationHandlerWayExceptionResolve(HttpServletRequest request,
			HttpServletResponse response, HandlerMethod handlerMethod, Exception ex);

	/**
	 * 推断处理器响应方式。支持返回 null 。 返回 null 代表不确定的响应方式
	 * 
	 * @param handlerMethod 处理器方法
	 * @return 处理器的响应方式
	 */
	@Nullable
	protected HandlerResponseWay deduceHandlerResponseWay(HandlerMethod handlerMethod) {
		return HandlerResponseWay.handlerResponseWayResolver(handlerMethod);
	}

	public void setJsonResponseExceptionResolver(JSONResponseExceptionResolver jsonResponseExceptionResolver) {
		this.jsonResponseExceptionResolver = jsonResponseExceptionResolver;
	}

	public void setViewResponseExceptionResolver(ViewResponseExceptionResolver viewResponseExceptionResolver) {
		this.viewResponseExceptionResolver = viewResponseExceptionResolver;
	}

}
