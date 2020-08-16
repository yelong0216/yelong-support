/**
 * 
 */
package org.yelong.support.json.gson.adapter;

import java.io.IOException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.yelong.commons.util.Dates;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * java.util.Date类型适配器<br/>
 * 
 * 对于Date的属性在序列化或者反序列化时，遇到“空”值时使用填补值来返回<br/>
 * 
 * 可以设置序列化时Date的格式与反序列化时如果解析这个字符串为Date<br/>
 * 
 * @since 1.1
 */
public class DateTypeAdapter extends TypeAdapter<Date> {

	public static final String DEFAULT_SERIALIZATION_NULL_PADDING = "";

	public static final Date DEFAULT_DESERIALIZATION_NULL_PADDING = null;

	public static final String DEFAULT_FORMAT = Dates.YYYY_MM_DD_HH_MM_SS;

	public static final String[] DEFAULT_PARSE_PATTERNS = { Dates.YYYY_MM_DD_HH_MM_SS, Dates.YYYY_MM_DD_BAR,
			Dates.YYYY_MM_DD_SLASH };

	/**
	 * 序列化null值填补
	 */
	private String serializationNullPadding;

	/**
	 * 反序列化null值填补
	 */
	private Date deserializationNullPadding;

	/**
	 * 反序列化时对Date的解析格式
	 */
	private String[] parsePatterns = DEFAULT_PARSE_PATTERNS;

	/**
	 * 转换Json时对Date的格式化格式
	 */
	private String format = DEFAULT_FORMAT;

	public DateTypeAdapter() {
		this(DEFAULT_SERIALIZATION_NULL_PADDING, DEFAULT_DESERIALIZATION_NULL_PADDING);
	}

	/**
	 * @param serializationNullPadding   序列化null值填补
	 *                                   当String类型进行转换json时，如果这个属性值为null则使用{@link #serializationNullPadding}来替换
	 * @param deserializationNullPadding 反序列化null值填补 反序列化null值填补
	 *                                   当json进行转换String类型时，如果这个属性值为null则使用{@link #deserializationNullPadding}来替换
	 */
	public DateTypeAdapter(String serializationNullPadding, Date deserializationNullPadding) {
		this.serializationNullPadding = serializationNullPadding;
		this.deserializationNullPadding = deserializationNullPadding;
	}

	@Override
	public void write(JsonWriter out, Date value) throws IOException {
		if (value == null) {
			out.value(serializationNullPadding);
			return;
		}
		if (null == format) {
			out.value(value.toString());
		} else {
			out.value(DateFormatUtils.format(value, format));
		}
	}

	@Override
	public Date read(JsonReader in) throws IOException {
		if (in.peek() == JsonToken.NULL) {
			in.nextNull();
			return deserializationNullPadding;
		}
		String str = in.nextString();
		try {
			return DateUtils.parseDateStrictly(str, parsePatterns);
		} catch (Exception e) {
			return null;
		}
	}

	public String getSerializationNullPadding() {
		return serializationNullPadding;
	}

	public DateTypeAdapter setSerializationNullPadding(String serializationNullPadding) {
		this.serializationNullPadding = serializationNullPadding;
		return this;
	}

	public Date getDeserializationNullPadding() {
		return deserializationNullPadding;
	}

	public DateTypeAdapter setDeserializationNullPadding(Date deserializationNullPadding) {
		this.deserializationNullPadding = deserializationNullPadding;
		return this;
	}

	public String[] getParsePatterns() {
		return parsePatterns;
	}

	public DateTypeAdapter setParsePatterns(String... parsePatterns) {
		this.parsePatterns = parsePatterns;
		return this;
	}

	public String getFormat() {
		return format;
	}

	public DateTypeAdapter setFormat(String format) {
		this.format = format;
		return this;
	}

}
