package com.chenjw.dto;

import com.fasterxml.jackson.annotation.JsonView;

public class User {

	public interface userSimpleView{};
	public interface userDatailView extends userSimpleView{};
	
	private String userName;
	private String password;
	
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
	
	
}
