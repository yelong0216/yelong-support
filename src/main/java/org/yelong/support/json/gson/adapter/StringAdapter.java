/**
 * 
 */
package org.yelong.support.json.gson.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * @author 彭飞
 * @date 2019年7月18日上午11:50:57
 */
public class StringAdapter extends TypeAdapter<String>{

	private final String nullPaddingValue;

	public StringAdapter() {
		this(null);
	}

	public StringAdapter(String nullPaddingValue) {
		this.nullPaddingValue = nullPaddingValue;
	}

	@Override
	public void write(JsonWriter out, String value) throws IOException {
		if( value == null ) {
			out.value(nullPaddingValue);
			return;
		}
		out.value(value);
	}

	@Override
	public String read(JsonReader in) throws IOException {
		if( in.peek() == JsonToken.NULL) {
			in.nextNull();
			return "";
		}
		return in.nextString();
	}

}
