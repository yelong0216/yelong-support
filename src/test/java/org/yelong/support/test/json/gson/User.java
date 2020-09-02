/**
 * 
 */
package org.yelong.support.test.json.gson;

import java.util.Date;

import org.yelong.core.model.Modelable;
import org.yelong.core.model.sql.SqlModel;

/**
 * @author PengFei
 * @since
 */
public class User extends SqlModel<User> implements Modelable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5017941758721872615L;
	
	private String id = "123";
	
	private String username = "yelong";
	
	private String password = null;
	
	private Integer age = null;
	
	private Date createTime = null;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
