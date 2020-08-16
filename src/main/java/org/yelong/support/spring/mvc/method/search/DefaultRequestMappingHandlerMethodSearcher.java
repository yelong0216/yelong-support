/**
 * 
 */
package org.yelong.support.spring.mvc.method.search;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * 默认实现
 * 
 * @since 2.0
 */
public class DefaultRequestMappingHandlerMethodSearcher implements RequestMappingHandlerMethodSearcher {

	private final RequestMappingHandlerMapping requestMappingHandlerMapping;

	public DefaultRequestMappingHandlerMethodSearcher(RequestMappingHandlerMapping requestMappingHandlerMapping) {
		this.requestMappingHandlerMapping = requestMappingHandlerMapping;
	}

	@Override
	public Map<RequestMappingInfo, HandlerMethod> search(SearchCondition searchCondition) {
		if (null == searchCondition) {
			return requestMappingHandlerMapping.getHandlerMethods();
		}
		return search(Arrays.asList(searchCondition));
	}

	@Override
	public Map<RequestMappingInfo, HandlerMethod> search(List<SearchCondition> searchConditions) {
		Map<RequestMappingInfo, HandlerMethod> handlerMethods = requestMappingHandlerMapping.getHandlerMethods();
		if (CollectionUtils.isEmpty(searchConditions)) {
			return handlerMethods;
		}
		Map<RequestMappingInfo, HandlerMethod> searchAfterHandlerMethods = new HashMap<RequestMappingInfo, HandlerMethod>();
		for (Entry<RequestMappingInfo, HandlerMethod> entry : handlerMethods.entrySet()) {
			RequestMappingInfo requestMappingInfo = entry.getKey();
			HandlerMethod handlerMethod = entry.getValue();
			if (matches(requestMappingInfo, handlerMethod, searchConditions)) {
				searchAfterHandlerMethods.put(requestMappingInfo, handlerMethod);
			}
		}
		return searchAfterHandlerMethods;
	}

	@Override
	public boolean matches(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod,
			SearchCondition searchCondition) {
		SearchName searchName = searchCondition.getSearchName();
		SearchMode searchMode = searchCondition.getSearchMode();
		String searchValue = searchCondition.getSearchValue();
		switch (searchName) {
		case NAME:
			String name = requestMappingInfo.getName();
			return matcheValue(name, searchMode, searchValue);
		case PATTERN:
			PatternsRequestCondition patternsCondition = requestMappingInfo.getPatternsCondition();
			Set<String> patterns = patternsCondition.getPatterns();
			for (String pattern : patterns) {
				if (matcheValue(pattern, searchMode, searchValue)) {
					return true;
				}
			}
			return false;
		case METHOD:
			RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
			Set<RequestMethod> methods = methodsCondition.getMethods();
			for (RequestMethod requestMethod : methods) {
				if (matcheValue(requestMethod.toString(), searchMode, searchValue)) {
					return true;
				}
			}
			return false;
		default:
			throw new UnsupportedOperationException("不支持的SearchName[" + searchName + "]");
		}
	}

	@Override
	public boolean matches(RequestMappingInfo requestMappingInfo, HandlerMethod handlerMethod,
			List<SearchCondition> searchConditions) {
		for (SearchCondition searchCondition : searchConditions) {
			if (!matches(requestMappingInfo, handlerMethod, searchCondition)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public RequestMappingHandlerMapping getRequestMappingHandlerMapping() {
		return this.requestMappingHandlerMapping;
	}

	protected boolean matcheValue(String value, SearchMode searchMode, String searchValue) {
		if (null == value) {
			return false;
		}
		switch (searchMode) {
		case PREFIX:
			return value.startsWith(searchValue);
		case EQ:
			return value.equals(searchValue);
		case CONTAINS:
			return value.contains(searchValue);
		case SUFFIX:
			return value.endsWith(searchValue);
		default:
			return false;
		}
	}

}
