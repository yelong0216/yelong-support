/**
 * 
 */
package org.yelong.support.orm.mybaits.model;

import org.apache.ibatis.session.SqlSession;
import org.yelong.core.model.service.ModelService;
import org.yelong.support.orm.mybaits.mapper.MyBatisBaseDataBaseOperation;

/**
 * mybatis model service
 * 
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月6日下午5:51:36
 * @version 1.2
 */
public interface MyBatisModelService extends ModelService{

	/**
	 * @date 2019年11月6日下午5:52:12
	 * @version 1.2
	 * @return sql session
	 */
	SqlSession getSqlSession();
	
	/**
	 * @date 2019年11月6日下午5:52:52
	 * @version 1.2
	 * @return mybatis 数据库操作对象
	 */
	MyBatisBaseDataBaseOperation getMyBatisBaseDataBaseOperation();
	
}
