/**
 * 
 */
package org.yelong.support.test.json.gson;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.yelong.support.json.gson.adapter.DateTypeAdapter;
import org.yelong.support.json.gson.adapter.IntegerTypeAdapter;
import org.yelong.support.json.gson.adapter.StringTypeAdapter;
import org.yelong.support.json.gson.model.sql.SqlModelExclusionStrategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author PengFei
 * @since
 */
public class GsonTest {

	@Test
	public void adapter() {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.registerTypeAdapter(Integer.class, new IntegerTypeAdapter("--", null));
		gsonBuilder.registerTypeAdapter(Date.class, new DateTypeAdapter("--", null));
		gsonBuilder.registerTypeAdapter(String.class, new StringTypeAdapter("--", null));
		gsonBuilder.setExclusionStrategies(SqlModelExclusionStrategy.DEFAULT);
		Gson gson = gsonBuilder.create();
		System.out.println(gson.toJson(new User()));
		
	}
	
}
