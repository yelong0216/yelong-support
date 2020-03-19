/**
 * 
 */
package org.yelong.support.json.gson.model;

import java.lang.reflect.Type;

import org.apache.commons.lang3.StringUtils;
import org.yelong.core.model.ModelNullProperty;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月29日下午2:02:37
 * @version 1.3
 */
public class ModelCharacterJsonDeserializer implements JsonDeserializer<Character>{
	
	@Override
	public Character deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if( null == json || StringUtils.isBlank(json.getAsString())) {
			return ModelNullProperty.CHAR_NULL;
		}
		return json.getAsCharacter();
	}
	

}
