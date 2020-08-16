package org.yelong.support.spring.mvc.method.search;

import java.util.Objects;

/**
 * 检索条件
 * 
 * @since 2.0
 */
public class SearchCondition {

	private final SearchName searchName;

	private final SearchMode searchMode;

	private final String searchValue;

	public SearchCondition(SearchName searchName, SearchMode searchMode, String searchValue) {
		this.searchName = Objects.requireNonNull(searchName);
		this.searchMode = Objects.requireNonNull(searchMode);
		this.searchValue = Objects.requireNonNull(searchValue);
	}

	public SearchName getSearchName() {
		return searchName;
	}

	public SearchMode getSearchMode() {
		return searchMode;
	}

	public String getSearchValue() {
		return searchValue;
	}

}
