/**
 * 
 */
package org.yelong.support.orm.mybaits.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.yelong.core.annotation.Nullable;
import org.yelong.support.orm.mybaits.sql.MyBatisParamMap;

/**
 * @author 彭飞
 * @date 2019年9月30日上午11:30:23
 * @version 1.2
 */
public class MyBatisMapperParamUtils {

	/**
	 * 将原生sql(select * from USER where username like ?)转换为mybatis使用的sql(select * from USER where username like #{username})
	 * 并将参数一并封装到map中。
	 * Mapper映射器中的参数仅传入一个Map。且sql语句为${sql}进行声明即可
	 * @author 彭飞
	 * @date 2019年7月26日下午6:30:22
	 * @version 1.0
	 * @param sql
	 * @param params
	 * @return
	 */
	public static Map<String,Object> getMyBatisMapperParams(String sql ,@Nullable Object [] params){
		Map<String,Object> mybatisParams = new HashMap<String,Object>();
		StringBuilder sb = new StringBuilder(sql);
		if( null != params && params.length > 0 ) {
			for (int i = 0; i < params.length; i++) {
				int index = sb.indexOf("?");
				String paramName = generateParamName();
				mybatisParams.put(paramName, params[i]);
				String mybatisType = MyBatisParamTypeUtils.getParamTypeMappingMyBatisType(params[i]);
				//设置字段类型
				if(StringUtils.isNotEmpty(mybatisType)) {
					paramName += ",jdbcType = "+mybatisType;
				}
				sb.replace(index, index+1, " #{"+paramName+"} ");
			}
		}
		mybatisParams.put("sql", sb.toString());
		return mybatisParams;
	}

	public static String generateParamName() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}
	
	
	/**
	 * 
	 * @author 彭飞
	 * @date 2019年9月11日下午5:45:37
	 * @version 1.2
	 * @param sql
	 * @param mybatisParamMap
	 * @return
	 */
	public static Map<String,Object> getMyBatisMapperParams(String sql ,@Nullable MyBatisParamMap mybatisParamMap){
		Map<String,Object> params = new HashMap<String, Object>(2);
		params.put("sql", sql);
		if( null != mybatisParamMap ) {
			params.put(mybatisParamMap.getMybatisParamMapPropertyName(), mybatisParamMap);
		}
		return params;
	}
	
	
	
	
	
	
}
