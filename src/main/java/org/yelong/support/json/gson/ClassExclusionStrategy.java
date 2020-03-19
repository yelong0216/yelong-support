/**
 * 
 */
package org.yelong.support.json.gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月23日下午4:06:01
 * @version 1.3
 */
public class ClassExclusionStrategy implements ExclusionStrategy{

	private final List<Class<?>> ignoreClasss = new ArrayList<>();

	private final Map<Class<?>,List<String>> ignoreClassFields = new HashMap<>();

	private List<Class<?>> onlyOperationClasss = new ArrayList<>();
	
	private Map<Class<?>,List<String>> onlyOperationClassFields = new HashMap<>();
	
	/**
	 * 添加忽略的类
	 * @date 2019年11月23日下午4:01:40
	 * @version 1.3
	 * @param classes
	 * @return
	 */
	public ClassExclusionStrategy addIgnoreClasss(Class<?> ... classes) {
		this.ignoreClasss.addAll(Arrays.asList(classes));
		return this;
	}
	
	/**
	 * 添加忽略的类的属性。
	 * 如果这个类已经被忽略({@link #addIgnoreClass(Class...)}，那么将不起作用
	 * @date 2019年11月23日下午4:01:40
	 * @version 1.3
	 * @param classes
	 * @return
	 */
	public ClassExclusionStrategy addIgnoreClassFields(Class<?> c , String ... fieldNames) {
		this.ignoreClassFields.put(c, Arrays.asList(fieldNames));
		return this;
	}

	/**
	 * 设置只操作的类型
	 * 设置之后忽略的类将不起作用，已只操作的类型为准
	 * @date 2019年11月29日下午2:08:47
	 * @version 1.3
	 * @param classes
	 * @return
	 */
	public ClassExclusionStrategy addOnlyOperationClasss(Class<?> ...classes) {
		onlyOperationClasss.addAll(Arrays.asList(classes));
		return this;
	}
	
	
	/**
	 * 添加只操作的类型属性。
	 * 如果这个类型不存在只操作的类型中将不起作用。
	 * 如果这个类已经被忽略({@link #addIgnoreClass(Class...)}，那么将不起作用
	 * @date 2019年11月23日下午4:01:40
	 * @version 1.3
	 * @param classes
	 * @return
	 */
	public ClassExclusionStrategy addOnlyOperationClassFields(Class<?> c , String ... fieldNames) {
		this.onlyOperationClassFields.put(c, Arrays.asList(fieldNames));
		return this;
	}
	
	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		if( this.onlyOperationClassFields.containsKey(f.getDeclaringClass()) ) {
			List<String> fieldList = this.onlyOperationClassFields.get(f.getDeclaringClass());
			if( null == fieldList) {
				return false;
			}
			return !fieldList.contains(f.getName());
		} else {
			List<String> fieldList = ignoreClassFields.get(f.getDeclaringClass());
			if( null == fieldList ) {
				return false;
			}
			return fieldList.contains(f.getName());
		}
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		if(onlyOperationClasss.isEmpty()) {
			return this.ignoreClasss.contains(clazz);
		} else {
			return !this.onlyOperationClasss.contains(clazz);
		}
	}

}
