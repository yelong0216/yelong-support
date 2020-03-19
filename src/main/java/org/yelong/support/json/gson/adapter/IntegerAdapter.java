/**
 * 
 */
package org.yelong.support.json.gson.adapter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * Gson中Integer类型适配器
 * @author 彭飞
 * @date 2019年10月17日下午3:44:26
 * @version 1.2
 */
public class IntegerAdapter extends TypeAdapter<Integer>{

	private final String nullPaddingValue;

	public IntegerAdapter() {
		this(null);
	}
	
	public IntegerAdapter(String nullPaddingValue) {
		this.nullPaddingValue = nullPaddingValue;
	}
	
	@Override
	public void write(JsonWriter out, Integer value) throws IOException {
		if( value == null ) {
			out.value(nullPaddingValue);
			return;
		}
		out.value(value);
	}

	@Override
	public Integer read(JsonReader in) throws IOException {
		if( in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		}
		String value = in.nextString();
		if( StringUtils.isEmpty(value) ) {
			return null;
		}
		return Integer.valueOf(value);
	}
	
}
