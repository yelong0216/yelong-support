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
 * java.lang.Integer类型适配器
 * 
 * 对于Integer的属性在序列化或者反序列化时，遇到“空”值时使用填补值来返回
 * 
 * @author PengFei
 * @since 1.0.5
 */
public class IntegerTypeAdapter extends TypeAdapter<Integer>{

	public static final String DEFAULT_SERIALIZATION_NULL_PADDING = "";
	
	public static final Integer DEFAULT_DESERIALIZATION_NULL_PADDING = null;
	
	/**
	 * 序列化null值填补
	 */
	private String serializationNullPadding;
	
	/**
	 * 反序列化null值填补
	 */
	private Integer deserializationNullPadding;
	
	
	public IntegerTypeAdapter() {
		this(DEFAULT_SERIALIZATION_NULL_PADDING,DEFAULT_DESERIALIZATION_NULL_PADDING);
	}
	
	/**
	 * @param serializationNullPadding 序列化null值填补 当String类型进行转换json时，如果这个属性值为null则使用{@link #serializationNullPadding}来替换
	 * @param deserializationNullPadding 反序列化null值填补 反序列化null值填补 当json进行转换String类型时，如果这个属性值为null则使用{@link #deserializationNullPadding}来替换
	 */
	public IntegerTypeAdapter(String serializationNullPadding, Integer deserializationNullPadding) {
		this.serializationNullPadding = serializationNullPadding;
		this.deserializationNullPadding = deserializationNullPadding;
	}

	@Override
	public void write(JsonWriter out, Integer value) throws IOException {
		if( value == null ) {
			out.value(serializationNullPadding);
			return;
		}
		out.value(value);
	}

	@Override
	public Integer read(JsonReader in) throws IOException {
		if( in.peek() == JsonToken.NULL) {
			in.nextNull();
			return deserializationNullPadding;
		} 
		return in.nextInt();
	}

	public String getSerializationNullPadding() {
		return serializationNullPadding;
	}

	public IntegerTypeAdapter setSerializationNullPadding(String serializationNullPadding) {
		this.serializationNullPadding = serializationNullPadding;
		return this;
	}

	public Integer getDeserializationNullPadding() {
		return deserializationNullPadding;
	}

	public IntegerTypeAdapter setDeserializationNullPadding(Integer deserializationNullPadding) {
		this.deserializationNullPadding = deserializationNullPadding;
		return this;
	}
	
}
