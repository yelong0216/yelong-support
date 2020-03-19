/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * mybatis基础操作映射类
 * @author 彭飞
 * @date 2019年7月18日下午1:28:20
 */
public interface MyBatisDBOperationMapper {
	
	
	/**
	 * 执行原生sql，参数的键值必须要有sql的键。如果需要参数，为防止sql注入，建议如下：
	 * {sql = select * from user where id = #{id} }<br/>
	 * 注意 这种场景不支持 $，必须使用#
	 * @author 彭飞
	 * @date 2019年7月18日下午1:32:47
	 * @param params 
	 * @return
	 * @throws SQLException
	 */
	@Select("${sql}")
	List<Map<String,Object>> select(Map<String,Object> params);
	
	/**
	 * 
	 * @author 彭飞
	 * @date 2019年7月26日下午6:28:13
	 * @version 1.0
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@Select("${sql}")
	List<Object> selectByColumn(Map<String,Object> params);
	
	
	
	/**
	 * 增删改操作
	 * @author 彭飞
	 * @date 2019年7月26日下午6:56:08
	 * @version 1.0
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	@Update("${sql}")
	Integer update(Map<String,Object> params);
	
	
}
