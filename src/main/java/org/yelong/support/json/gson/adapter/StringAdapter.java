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
 * String 类型适配器
 * @author PengFei
 * @deprecated 使用{@link StringTypeAdapter}更好的操作String与json的转换
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
