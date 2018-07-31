package com.imooc.dto;

import java.util.Date;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * @author Beauxie
 * @date 2018年7月29日
 */
public class User {
	private String id;
	private String username;
	@NotBlank
	private String password;
	private Date birthday;
 
	@JsonView(UserSimpleView.class)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	/**
	 *  注解@JsonView的使用步骤:
	 *  1.使用接口声明多个视图
	 *  2.在值对象的get方法上指定视图
	 *  3.在Controller方法上指定视图
	 * @author Beauxie
	 * @date   2018年7月29日
	 */
	
	public  interface UserSimpleView{};
	public interface UserDetailView extends UserSimpleView{};

}
