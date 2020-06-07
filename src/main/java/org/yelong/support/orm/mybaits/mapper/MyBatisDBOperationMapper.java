/**
 * 
 */
package org.yelong.support.orm.mybaits.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * mybatis基础操作映射类
 * 
 * @author PengFei
 */
public interface MyBatisDBOperationMapper {
	
	/**
	 * 执行原生sql，参数的键值必须要有sql的键。如果需要参数，为防止sql注入，建议如下：
	 * {sql = select * from user where id = #{id} }<br/>
	 * 注意 这种场景不支持 $，必须使用#
	 * 
	 * @param params  参数
	 * @return sql执行结果
	 */
	@Select("${sql}")
	List<Map<String,Object>> select(Map<String,Object> params);
	
	/**
	 * 查询一列数据
	 * 执行原生sql，参数的键值必须要有sql的键。如果需要参数，为防止sql注入，建议如下：
	 * {sql = select * from user where id = #{id} }<br/>
	 * 注意 这种场景不支持 $，必须使用#
	 * 
	 * @param params 参数 
	 * @return 查询的一列数据
	 */
	@Select("${sql}")
	List<Object> selectByColumn(Map<String,Object> params);
	
	/**
 	 * 执行原生sql，参数的键值必须要有sql的键。如果需要参数，为防止sql注入，建议如下：
	 * {sql = select * from user where id = #{id} }<br/>
	 * 注意 这种场景不支持 $，必须使用#
	 * 增删改操作
	 * 
	 * @param params 参数
	 * @return 执行的结果
	 */
	@Update("${sql}")
	Integer update(Map<String,Object> params);
	
}
