/**
 * 
 */
package org.yelong.support.spring.mvc.token;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;

/**
 * @author 彭飞
 * @date 2019年8月30日下午4:40:42
 * @version 1.2
 */
public abstract class AbstractRequestUserInfo implements RequestUserInfo{

	private String token;

	public AbstractRequestUserInfo(String token) {
		this.token = token;
	}

	@Override
	public String getToken() {
		return token;
	}

	@Override
	public Object get(String key) {
		try {
			return BeanUtils.getProperty(this, key);
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public String[] getKeys() {
		List<String> fieldNameList = new ArrayList<String>();
		Class<?> c = getClass();
		fieldNameList.addAll(Arrays.asList(c.getDeclaredFields()).stream().map(x->x.getName()).collect(Collectors.toList()));
		Class<?> superClass = c.getSuperclass();
		while(true) {
			if( superClass == AbstractRequestUserInfo.class ) {
				break;
			}
			fieldNameList.addAll(Arrays.asList(superClass.getDeclaredFields()).stream().map(x->x.getName()).collect(Collectors.toList()));
			superClass = superClass.getSuperclass();
		}
		fieldNameList.add("token");
		return fieldNameList.toArray(new String[] {});
	}



}
