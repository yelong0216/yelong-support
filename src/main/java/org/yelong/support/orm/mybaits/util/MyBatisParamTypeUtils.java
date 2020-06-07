/**
 * 
 */
package org.yelong.support.orm.mybaits.util;

import java.math.BigDecimal;
import java.util.Date;

/**
 * MyBatis参数类型工具<br/>
 * 
 * @author PengFei
 */
public class MyBatisParamTypeUtils {
	
	/**
	 * 获取参数映射的MyBatis类型<br/>
	 * 默认是oracle<br/>
	 * 这个类型应用于mapper.xml中 #{id,VARCHAR}这种情况
	 * 
	 * @param param 参数值
	 * @return MyBatis的映射类型
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
