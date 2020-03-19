/**
 * 
 */
package org.yelong.support.orm.mybaits.sql;

import org.apache.ibatis.annotations.Param;

/**
 * 参数别名支持。
 * 实现此接口的可以通过拦截器设置其{@link Param}的别名。
 * 此别名会修改参数的key值，以适应ognl表达式
 * @author 彭飞
 * @date 2019年9月29日下午5:31:00
 * @version 1.2
 */
public interface MyBatisParamAliasable {


	/**
	 * 设置别名<br/>
	 * 这个别名如同{@link Param}注解一样<br/>
	 * 你也可以直接使用{@link Param}注解进行别标注<br/>
	 * 设置别名之后你需要使用${paramAlias.placeholder}等格式在mapper.xml中进行使用<br/>
	 * @author 彭飞
	 * @date 2019年8月9日下午12:01:40
	 * @version 1.0
	 * @param paramAlias 参数别名。
	 */
	void setParamAlias(String paramAlias);


}
