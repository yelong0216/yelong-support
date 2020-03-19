/**
 * 
 */
package org.yelong.support.json.gson;

import org.yelong.support.json.gson.adapter.StringAdapter;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

/**
 * @author 彭飞
 * @date 2019年7月18日上午11:54:29
 */
@Deprecated
public class NullStringToEmptyAdapterFactory implements TypeAdapterFactory{

	@Override
	@SuppressWarnings("unchecked")
	public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
		Class<T> rawType = (Class<T>) type.getRawType();
		if( rawType != String.class) {
			return null;
		}
		return (TypeAdapter<T>)new StringAdapter();
	}
	
}
