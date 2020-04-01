/**
 * 
 */
package org.yelong.support.orm.mybaits.model.mapping;

import java.util.List;

import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.session.Configuration;
import org.yelong.core.model.resolve.ModelAndTable;

/**
 * @author PengFei
 */
public interface ResultMappingBuilder {

	List<ResultMapping> build(ModelAndTable modelAndTable , Configuration configuration);
	
}
