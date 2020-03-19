/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.yelong.support.orm.mybaits.util.MyBatisParamTypeUtils;

/**
 * mybatis参数<br/>
 * 该对象用来存储mybatis映射的参数<br/>
 * 它应该用在成员变量中<br/>
 * 让其ognl表达式来获取参数值
 * @author 彭飞
 * @date 2019年8月28日上午10:12:29
 * @version 1.2
 */
public class MyBatisParamMap extends LinkedHashMap<String, Object> implements MyBatisParamAliasable{

	private static final long serialVersionUID = -9183829445858411496L;

	private static String paramNamePrefix = "PARAM";
	
	private static long paramNameFlag = 0;
	
	/**占位符参数映射，其key为sql中使用的占位符*/
	private final Map<String,Object> PLACEHOLDER_PARAM_MAP = new LinkedHashMap<>();
	
	private String paramAlias;

	private final String mybatisParamMapPropertyName;	
	
	private static final String DOT = ".";
	
	/**
	 * @param mybatisParamMapPropertyName 该属性在其对象中的变量名称
	 */
	public MyBatisParamMap(final String mybatisParamMapPropertyName) {
		this.mybatisParamMapPropertyName = mybatisParamMapPropertyName;
	}
	
	/**
	 * 添加一个参数映射<br/>
	 * 返回该参数值的mybatis占位符<br/>
	 * @author 彭飞
	 * @date 2019年8月23日下午3:49:08
	 * @version 1.2
	 * @param value 参数值
	 * @param jdbcType jdbc类型
	 * @return #{ paramPlaceholder,jdbcType = mybatisParamTypeMap }
	 */
	public String addParamMap(Object value , String jdbcType) {
		StringBuilder paramPlaceholder = new StringBuilder("#{");
		//如果存在别名
		if(existParamAlias()) {
			paramPlaceholder.append(paramAlias);
			paramPlaceholder.append(DOT);
		}
		//该对象在其它对象的参数名称
		paramPlaceholder.append(mybatisParamMapPropertyName);
		paramPlaceholder.append(DOT);
		//参数名称
		String dynamicParamPlaceholder = generateParamPlaceholder();
		paramPlaceholder.append(dynamicParamPlaceholder);
		//这个放入map中，使其ognl表达式获取
		put(dynamicParamPlaceholder, value);
		//设置字段类型
		if(StringUtils.isNotEmpty(jdbcType)) {
			paramPlaceholder.append(",jdbcType = "+jdbcType);
		}
		paramPlaceholder.append("}");
		this.PLACEHOLDER_PARAM_MAP.put(paramPlaceholder.toString(), value);
		return paramPlaceholder.toString();
	}
	
	
	/**
	 * 添加一个参数映射<br/>
	 * 返回该参数值的mybatis占位符<br/>
	 * @author 彭飞
	 * @date 2019年8月23日下午3:49:08
	 * @version 1.2
	 * @param value 参数值 根据该值类型获取 jdbcType
	 * @return #{ paramPlaceholder,jdbcType = mybatisParamTypeMap }
	 */
	public String addParamMap(Object value) {
		return addParamMap(value, MyBatisParamTypeUtils.getParamTypeMappingMyBatisType(value));
	}
	
	/**
	 * 设置参数别名<br/>
	 * 这应该是{@link Param 的value值}
	 * @author 彭飞
	 * @date 2019年8月23日下午3:45:35
	 * @version 1.2
	 * @param paramAlias
	 */
	public void setParamAlias(String paramAlias) {
		//如果设置同样的参数别名，则不进行处理
		if(existParamAlias() && this.paramAlias.equals(paramAlias)) {
			return ;
		} else if( !existParamAlias() ) {
			Map<String,Object> placeholderParamMap = new LinkedHashMap<>(this.size());
			PLACEHOLDER_PARAM_MAP.forEach((x,y)->{
				x = x.substring(x.indexOf("#{")+2, x.indexOf("}"));
				placeholderParamMap.put("#{"+paramAlias+DOT+x+"}", y);
			});
			PLACEHOLDER_PARAM_MAP.clear();
			PLACEHOLDER_PARAM_MAP.putAll(placeholderParamMap);
			
		} else {
			Map<String,Object> placeholderParamMap = new LinkedHashMap<>(this.size());
			PLACEHOLDER_PARAM_MAP.forEach((x,y)->{
				x = x.substring(x.indexOf("#{")+2, x.indexOf("}"));
				x = x.substring(x.indexOf(DOT));
				placeholderParamMap.put("#{"+paramAlias+DOT+x+"}", y);
			});
			PLACEHOLDER_PARAM_MAP.clear();
			PLACEHOLDER_PARAM_MAP.putAll(placeholderParamMap);
		}
		this.paramAlias = paramAlias;
	}

	public String getParamAlias() {
		return paramAlias;
	}
	
	public String getMybatisParamMapPropertyName() {
		return mybatisParamMapPropertyName;
	}
	
	/**
	 * 是否存在参数别名
	 * @author 彭飞
	 * @date 2019年8月23日下午3:52:06
	 * @version 1.2
	 * @return <tt>true</tt> 存在别名
	 */
	public boolean existParamAlias() {
		return StringUtils.isNotEmpty(paramAlias);
	}
	
	@Override
	public void clear() {
		PLACEHOLDER_PARAM_MAP.clear();
		super.clear();
	}
	
	/**
	 * 获取占位符参数映射
	 * @author 彭飞
	 * @date 2019年8月28日上午10:59:51
	 * @version 1.2
	 * @return
	 */
	public Map<String,Object> getPlaceholderParamMap(){
		return Collections.unmodifiableMap(PLACEHOLDER_PARAM_MAP);
	}
	
	/**
	 * 添加参数映射<br/>
	 * 这不会添加占位符映射
	 * @author 彭飞
	 * @date 2019年8月28日下午2:44:30
	 * @version 1.2
	 * @param mybatisParamMap
	 */
	public void putMyBatisParamMap(MyBatisParamMap mybatisParamMap){
		this.putAll(mybatisParamMap);
	}
	
	/**
	 * 生成参数占位符
	 * @author 彭飞
	 * @date 2019年8月28日下午4:52:55
	 * @version 1.2
	 * @return
	 */
	private synchronized String generateParamPlaceholder() {
		if(paramNameFlag == 1000) {
			paramNameFlag = 0;
		}
		String paramPlaceholder = paramNamePrefix+paramNameFlag;
		paramNameFlag++;
		return paramPlaceholder;
	}
	
	
}
