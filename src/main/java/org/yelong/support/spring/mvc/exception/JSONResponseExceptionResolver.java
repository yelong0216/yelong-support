package org.yelong.support.spring.mvc.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.yelong.core.annotation.Nullable;

/**
 * JSON响应异常处理器
 * 
 * 在响应JSON数据时出现异常的回调的接口。如请求参数的数据验证失败异常，参数不正确异常等.
 * 
 * @since 2.2
 */
public interface JSONResponseExceptionResolver {

	/**
	 * 解析异常并给予前台响应结果
	 * 
	 * @param request       当前HTTP请求
	 * @param response      当前的HTTP响应
	 * @param handlerMethod 执行的处理程序，或{@code null}(如果在异常时没有选择)(例如，如果多部分解析失败)
	 * @param ex            在处理程序执行期间抛出的异常
	 * @return 要转发的对应的{@code ModelAndView}，或用于解析链中默认处理的{@code null}
	 */
	@Nullable
	ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response,
			@Nullable HandlerMethod handlerMethod, Exception ex);

}
