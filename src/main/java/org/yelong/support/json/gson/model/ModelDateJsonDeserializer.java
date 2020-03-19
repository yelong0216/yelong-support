/**
 * 
 */
package org.yelong.support.json.gson.model;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.yelong.core.model.ModelNullProperty;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * @author pengfei<yl1430834495@163.com>
 * @date 2019年11月29日下午1:55:43
 * @version 1.3
 */
public class ModelDateJsonDeserializer implements JsonDeserializer<Date>{

	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if( null == json || StringUtils.isBlank(json.getAsString())) {
			return ModelNullProperty.DATE_NULL;
		}
		//return context.deserialize(json, typeOfT);
		try {
			return DateUtils.parseDate(json.getAsString(), "yyyy-MM-dd");
		} catch (ParseException e) {
			throw new JsonParseException(e);
		}
	}

}
