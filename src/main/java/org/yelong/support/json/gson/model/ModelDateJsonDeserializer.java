/**
 * 
 */
package org.yelong.support.json.gson.model;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.yelong.commons.util.Dates;
import org.yelong.core.model.ModelNullProperty;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

/**
 * @author PengFei
 */
public class ModelDateJsonDeserializer implements JsonDeserializer<Date>{

	private final String [] parsePatterns;
	
	public ModelDateJsonDeserializer() {
		this(Dates.YYYY_MM_DD_HH_MM_SS,Dates.YYYY_MM_DD_BAR,Dates.YYYY_MM_DD_SLASH);
	}
	
	public ModelDateJsonDeserializer(String ... parsePatterns) {
		this.parsePatterns = parsePatterns;
	}
 	
	@Override
	public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
			throws JsonParseException {
		if( null == json || StringUtils.isBlank(json.getAsString())) {
			return ModelNullProperty.DATE_NULL;
		}
		try {
			return DateUtils.parseDate(json.getAsString(),parsePatterns);
		} catch (ParseException e) {
			throw new JsonParseException(e);
		}
	}

}
