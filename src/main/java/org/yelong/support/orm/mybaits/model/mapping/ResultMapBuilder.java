/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping;

import java.util.List;

import org.apache.ibatis.builder.MapperBuilderAssistant;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.resolve.ModelAndTable;

/**
 * {@link ResultMap} 建造者
 * 
 * @author PengFei
 */
public interface ResultMapBuilder {

	/**
	 * @param modelAndTable modelAndTable
	 * @param configuration configuration
	 * @return ResultMap List
	 */
	List<ResultMap> build(ModelAndTable modelAndTable, Configuration configuration);

	/**
	 * @param modelAndTable modelAndTable
	 * @param assistant     assistant
	 * @return ResultMap List
	 */
	List<ResultMap> build(ModelAndTable modelAndTable, MapperBuilderAssistant assistant);

}
