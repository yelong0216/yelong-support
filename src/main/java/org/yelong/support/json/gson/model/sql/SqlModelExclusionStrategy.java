/**
 * 
 */
package org.yelong.support.json.gson.model.sql;

import java.util.ArrayList;
import java.util.List;

import org.yelong.core.model.sql.SqlModel;
import org.yelong.support.json.gson.ClassExclusionStrategy;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * 对于SqlModel进行转换json时，去除多余的字段
 * 
 * @since 1.1
 */
public class SqlModelExclusionStrategy implements ExclusionStrategy {

	private ClassExclusionStrategy classExclusionStrategy;

	// 需要忽略的字段
	private static final List<String> SQL_MODEL_EXCLUSION_FIELDS = new ArrayList<String>();

	static {
		// 静态块应该改应该属性上面。否则初始化的类中没有添加上字段
		SQL_MODEL_EXCLUSION_FIELDS.add("conditionOperators");
		SQL_MODEL_EXCLUSION_FIELDS.add("extendAttributes");
		SQL_MODEL_EXCLUSION_FIELDS.add("sortFields");
		SQL_MODEL_EXCLUSION_FIELDS.add("groupColumns");
		SQL_MODEL_EXCLUSION_FIELDS.add("conditions");
		SQL_MODEL_EXCLUSION_FIELDS.add("modelClass");
		SQL_MODEL_EXCLUSION_FIELDS.add("model");
	}

	public static final SqlModelExclusionStrategy DEFAULT = new SqlModelExclusionStrategy();

	private SqlModelExclusionStrategy() {
		classExclusionStrategy = new ClassExclusionStrategy();
		classExclusionStrategy.addIgnoreClassFields(SqlModel.class,
				SQL_MODEL_EXCLUSION_FIELDS.toArray(new String[] {}));
	}

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		return classExclusionStrategy.shouldSkipField(f);
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return classExclusionStrategy.shouldSkipClass(clazz);
	}

}
