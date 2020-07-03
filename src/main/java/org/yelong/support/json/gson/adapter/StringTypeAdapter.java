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
 * java.lang.String类型适配器<br/>
 * 
 * 对于String的属性在序列化或者反序列化时，遇到“空”值时使用填补值来返回
 * 
 * @author PengFei
 * @since 1.0.5
 */
public class StringTypeAdapter extends TypeAdapter<String> {

	public static final String DEFAULT_SERIALIZATION_NULL_PADDING = "";

	public static final String DEFAULT_DESERIALIZATION_NULL_PADDING = null;

	/**
	 * 序列化null值填补
	 */
	private String serializationNullPadding;

	/**
	 * 反序列化null值填补
	 */
	private String deserializationNullPadding;

	private boolean serializationBlankDeemedNull = true;

	private boolean deserializationBlankDeemedNull = false;

	public StringTypeAdapter() {
		this(DEFAULT_SERIALIZATION_NULL_PADDING, DEFAULT_DESERIALIZATION_NULL_PADDING);
	}

	/**
	 * @param serializationNullPadding   序列化null值填补
	 *                                   当String类型进行转换json时，如果这个属性值为null则使用{@link #serializationNullPadding}来替换
	 * @param deserializationNullPadding 反序列化null值填补 反序列化null值填补
	 *                                   当json进行转换String类型时，如果这个属性值为null则使用{@link #deserializationNullPadding}来替换
	 */
	public StringTypeAdapter(String serializationNullPadding, String deserializationNullPadding) {
		this.serializationNullPadding = serializationNullPadding;
		this.deserializationNullPadding = deserializationNullPadding;
	}

	@Override
	public void write(JsonWriter out, String value) throws IOException {
		if (value == null) {
			out.value(serializationNullPadding);
			return;
		}
		if (serializationBlankDeemedNull && StringUtils.isBlank(value)) {
			out.value(serializationNullPadding);
			return;
		}
		out.value(value);
	}

	@Override
	public String read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return deserializationNullPadding;
		}
		String value = in.nextString();
		if (deserializationBlankDeemedNull) {
			if (StringUtils.isBlank(value)) {
				return deserializationNullPadding;
			}
		}
		return value;
	}

	public String getSerializationNullPadding() {
		return serializationNullPadding;
	}

	public StringTypeAdapter setSerializationNullPadding(String serializationNullPadding) {
		this.serializationNullPadding = serializationNullPadding;
		return this;

	}

	public String getDeserializationNullPadding() {
		return deserializationNullPadding;
	}

	public StringTypeAdapter setDeserializationNullPadding(String deserializationNullPadding) {
		this.deserializationNullPadding = deserializationNullPadding;
		return this;
	}

	public boolean isSerializationBlankDeemedNull() {
		return serializationBlankDeemedNull;
	}

	/**
	 * 序列化时是否将空白字符认为时null
	 * 
	 * @param serializationBlankDeemedNull
	 */
	public StringTypeAdapter setSerializationBlankDeemedNull(boolean serializationBlankDeemedNull) {
		this.serializationBlankDeemedNull = serializationBlankDeemedNull;
		return this;
	}

	public boolean isDeserializationBlankDeemedNull() {
		return deserializationBlankDeemedNull;
	}

	/**
	 * 反序列化时是否将空白字符认为是null
	 * 
	 * @param serializationBlankDeemedNull
	 */
	public StringTypeAdapter setDeserializationBlankDeemedNull(boolean deserializationBlankDeemedNull) {
		this.deserializationBlankDeemedNull = deserializationBlankDeemedNull;
		return this;
	}

}
