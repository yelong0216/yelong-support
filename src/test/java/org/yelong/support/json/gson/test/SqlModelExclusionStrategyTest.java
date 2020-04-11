/**
 * 
 */
package org.yelong.support.json.gson.test;

import org.yelong.support.json.gson.model.sql.SqlModelExclusionStrategy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author PengFei
 * @since
 */
public class SqlModelExclusionStrategyTest{
	
	
	public static void main(String[] args) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.addSerializationExclusionStrategy(SqlModelExclusionStrategy.DEFAULT);
		Gson gson = gsonBuilder.create();
		User user = new User();
		System.out.println(gson.toJson(user));
	}
	
}
