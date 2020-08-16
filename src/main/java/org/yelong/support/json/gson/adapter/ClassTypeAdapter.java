/**
 * 
 */
package org.yelong.support.json.gson.adapter;

import java.io.IOException;

import org.apache.commons.lang3.ClassUtils;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * 类适配器
 */
public class ClassTypeAdapter extends TypeAdapter<Class<?>> {

	@Override
	public void write(JsonWriter out, Class<?> value) throws IOException {
		out.value(value.getName());
	}

	@Override
	public Class<?> read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		}
		String str = in.nextString();
		if (StringUtils.isBlank(str)) {
			return null;
		}
		try {
			return ClassUtils.getClass(str);
		} catch (ClassNotFoundException e) {
			in.nextNull();
			return null;
		}
	}

}
