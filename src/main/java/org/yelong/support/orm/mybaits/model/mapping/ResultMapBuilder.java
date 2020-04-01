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
 * @author PengFei
 *
 */
public interface ResultMapBuilder {
	
	List<ResultMap> build(ModelAndTable modelAndTable , Configuration configuration);
	
	List<ResultMap> build(ModelAndTable modelAndTable , MapperBuilderAssistant assistant);
	
}
