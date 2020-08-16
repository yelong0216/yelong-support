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
 * @since 1.1
 */
public class ModelByteJsonDeserializer implements JsonDeserializer<Byte> {

	@Override
	public Byte deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if (null == json || StringUtils.isBlank(json.getAsString())) {
			return ModelNullProperty.BYTE_NULL;
		}
		return json.getAsByte();
	}

}
