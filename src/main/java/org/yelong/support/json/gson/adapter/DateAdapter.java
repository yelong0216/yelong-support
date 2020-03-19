/**
 * 
 */
package org.yelong.support.json.gson.adapter;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * 如果Date类型属性为空则返回""。否则返回 yyyy-MM-dd HH:mm:ss 格式字符
 * @author 彭飞
 * @date 2019年7月26日上午11:11:32
 */
public class DateAdapter extends TypeAdapter<Date>{

	private final String nullPaddingValue;

	public DateAdapter() {
		this(null);
	}
	
	public DateAdapter(String nullPaddingValue) {
		this.nullPaddingValue = nullPaddingValue;
	}
	
	@Override
	public void write(JsonWriter out, Date value) throws IOException {
		if( value == null ) {
			out.value(nullPaddingValue);
			return;
		}
		out.value(DateFormatUtils.format(value, "yyyy-MM-dd HH:mm:ss"));
	}

	@Override
	public Date read(JsonReader in) throws IOException {
		if( in.peek() == JsonToken.NULL) {
			in.nextNull();
			return null;
		}
		String str = in.nextString();
		try {
			return DateUtils.parseDateStrictly(str, "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd");
		} catch (ParseException e) {
			return null;
		}
	}

	public static void main(String[] args) throws ParseException {
		Date date = DateUtils.parseDateStrictly("2019-10-10", "yyyy-MM-dd HH:mm:ss","yyyy-MM-dd");
		String string = DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
		System.out.println(string);
	}

}
