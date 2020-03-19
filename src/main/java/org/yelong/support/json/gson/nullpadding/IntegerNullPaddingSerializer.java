/**
 * 
 */
package org.yelong.support.json.gson.nullpadding;

import java.lang.reflect.Type;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author PengFei
 */
public class IntegerNullPaddingSerializer implements JsonSerializer<Integer>{

	private final String nullPaddingValue;

	public IntegerNullPaddingSerializer(String nullPaddingValue) {
		this.nullPaddingValue = nullPaddingValue;
	}

	public IntegerNullPaddingSerializer() {
		this(null);
	}

	@Override
	public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
		if( null == src ) {
			if( nullPaddingValue == null ) {
				return JsonNull.INSTANCE;
			} else {
				return new JsonPrimitive(nullPaddingValue);
			}
		}
		return  new JsonPrimitive(src);
	}

}
