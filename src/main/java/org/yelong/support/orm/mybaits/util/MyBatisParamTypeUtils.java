/**
 * 
 */
package org.yelong.support.orm.mybaits.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * mybatis参数类型工具<br/>
 * @author 彭飞
 * @date 2019年8月2日下午4:02:02
 * @version 1.0
 */
public class MyBatisParamTypeUtils {
	

	/**
	 * 获取参数映射的mybatis类型<br/>
	 * 默认是oracle<br/>
	 * 这个类型应用于mapper.xml中 #{id,VARCHAR}这种情况
	 * @author 彭飞
	 * @date 2019年8月1日下午6:32:54
	 * @version 1.0
	 * @param param
	 * @return mybatis的映射类型
	 */
	public static String getParamTypeMappingMyBatisType(Object param) {
		String dbType = "oracle";
		if("oracle".equals(dbType)) {
			if( null == param || param instanceof CharSequence ) {
				return "VARCHAR";
			} else if ( param instanceof Date) {
				return "TIMESTAMP";
			} else if ( param instanceof Number || param instanceof BigDecimal) {
				return "DECIMAL";
			} 
		}
		return "";
	}


}
