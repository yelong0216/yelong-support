package org.yelong.support.json.gson.test;

import org.yelong.core.model.sql.SqlModel;


public class User extends SqlModel{

	private static final long serialVersionUID = 1982504135145414804L;

	private String id = "123";
	
	private String username = "123";
	
	private String age = "123";

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
	
}
