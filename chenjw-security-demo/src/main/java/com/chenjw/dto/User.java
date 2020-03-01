package com.chenjw.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.chenjw.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;

public class User {

	public interface userSimpleView{};
	public interface userDatailView extends userSimpleView{};
	
	@NotBlank(message = "用户名不能为空")
	private String userName;
	@MyConstraint
	private String password;
	private String id;
	@Past(message = "生日必须是过去的时间")
	private Date birthday; 
	
	@JsonView(userSimpleView.class)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonView(userDatailView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonView(userSimpleView.class)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonView(userSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
	
}
