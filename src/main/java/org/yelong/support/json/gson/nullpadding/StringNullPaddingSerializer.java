/**
 * 
 */
package org.yelong.support.json.gson.nullpadding;

import java.lang.reflect.Type;

import org.yelong.support.json.gson.adapter.StringTypeAdapter;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

/**
 * @author PengFei
 * @see StringTypeAdapter
 */
public class StringNullPaddingSerializer implements JsonSerializer<String> {

	private final String nullPaddingValue;
	
	public StringNullPaddingSerializer(String nullPaddingValue) {
		this.nullPaddingValue = nullPaddingValue;
	}
	
	public StringNullPaddingSerializer() {
		this(null);
	}
	
	@Override
	public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
		if( null == src ) {
			if( nullPaddingValue == null ) {
				return JsonNull.INSTANCE;
			} else {
				return new JsonPrimitive(nullPaddingValue);
			}
		}
		return new JsonPrimitive(src);
	}
	
}
