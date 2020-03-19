/**
 * 
 */
package org.yelong.support.database.oracle;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Oracle条件运算符
 * @author 彭飞
 * @date 2019年7月19日下午3:07:30
 */
public enum OracleConditionOperator {
	
	/***/
	LIKE(" like "),
	/***/
	NOT_LIKE(" not like "),
	/**等于*/
	EQUAL(" = "),
	/**不等于*/
	NOT_EQUAL(" <> "),
	/**包含*/
	IN(" in "),
	/**不包含*/
	NOT_IN(" not in "),
	/**区间*/
	BETWEEN(" between "),
	/**不在指定的区间*/
	NOT_BETWEEN(" not between "),
	/**为空*/
	IS_NULL(" is null "),
	/**不为空*/
	IS_NOT_NULL(" is not null "),
	/**大于*/
	GREATER_THAN(" > "),
	/**大于等于*/
	GREATER_THAN_OR_EQUAL(" >= "),
	/**小于*/
	LESS_THAN(" < "),
	/**小于等于*/
	LESS_THAN_OR_EQUAL(" <= ");
	
	/**不需要值的条件*/
	public static final OracleConditionOperator [] NOT_NEED_VALUE_CONDITION = {
			IS_NULL,IS_NOT_NULL
	};
	/**需要一个值的条件*/
	public static final OracleConditionOperator [] NEED_ONE_VALUE_CONDITION = {
			LIKE,NOT_LIKE,EQUAL,NOT_EQUAL,GREATER_THAN,GREATER_THAN_OR_EQUAL,LESS_THAN,LESS_THAN_OR_EQUAL
	};
	/**需要两个值的条件*/
	public static final OracleConditionOperator [] NEED_TWO_VALUE_CONDITION = {
			BETWEEN,NOT_BETWEEN
	};
	/**任意个数量的条件值*/
	public static final OracleConditionOperator [] ANY_VALUE_CONDITION = {
			IN,NOT_IN
	};
	
	
	private String operator;
	
	OracleConditionOperator(String operator){
		this.operator = operator;
		
	}

	@Deprecated
	public String getCondition() {
		return operator;
	}
	
	public String getOperator() {
		return operator;
	}
	
	/**
	 * 该条件需要的值数量
	 * @author 彭飞
	 * @date 2019年7月19日下午4:01:28
	 * @return 该条件需要的值数量。如果条件需要不定量的值则返回-1
	 */
	public Integer getNeedValueNum() {
		if(ArrayUtils.contains(NOT_NEED_VALUE_CONDITION, this)) {
			return 0;
		} else if(ArrayUtils.contains(NEED_ONE_VALUE_CONDITION, this)) {
			return 1;
		} else if(ArrayUtils.contains(NEED_TWO_VALUE_CONDITION, this)) {
			return 2;
		} else if (ArrayUtils.contains(ANY_VALUE_CONDITION, this)) {
			return -1;
		}
		return null ;
	}
	
	
	
	

}
