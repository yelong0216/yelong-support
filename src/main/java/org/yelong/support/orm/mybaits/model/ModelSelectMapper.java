/**
 * 
 */
package org.yelong.support.orm.mybaits.model;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;
import org.yelong.core.model.Model;
import org.yelong.support.orm.mybaits.interceptor.ModelResultSetHandlerInteceptor;

/**
 * model 查询映射。
 * 通过拦截器生成model的结果集
 * @see 1.0.2
 * @deprecated 此类与{@link ModelResultSetHandlerInteceptor}联合使用。因为其类的弃用，该类也没有任何作用了。
 * @see ModelResultSetHandlerInteceptor
 * @author PengFei
 */
@Deprecated
public interface ModelSelectMapper {

	/**
	 * model查询。该查询根据拦截器默认映射结果集
	 * param中必有参数：sql(执行的sql语句)、modelTable(映射的结果集类型表映射)、modelAndTable(映射的结果集类型表映射)
	 * @param <M>
	 * @param param
	 * @return
	 */
	@Select("${sql}")
	<M extends Model> List<M> select(Map<String,Object> param);
	
}
	