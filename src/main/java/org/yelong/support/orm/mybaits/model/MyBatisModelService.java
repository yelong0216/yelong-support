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
 * @author PengFei
 */
public interface MyBatisModelService extends ModelService{

	/**
	 * @return sql session
	 */
	SqlSession getSqlSession();
	
	/**
	 * @return mybatis 数据库操作对象
	 */
	MyBatisBaseDataBaseOperation getMyBatisBaseDataBaseOperation();
	
}
