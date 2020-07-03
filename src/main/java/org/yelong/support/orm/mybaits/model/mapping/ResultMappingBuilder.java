/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping;

import java.util.List;

import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.resolve.ModelAndTable;

/**
 * {@link ResultMapping} 建造者
 * 
 * @author PengFei
 */
public interface ResultMappingBuilder {

	/**
	 * @param modelAndTable modelAndTable
	 * @param configuration configuration
	 * @return ResultMapping List
	 */
	List<ResultMapping> build(ModelAndTable modelAndTable, Configuration configuration);

}
