package com.chenjw.security.securityweb.model;

import lombok.Data;

@Data
public class UserModel {
	private String userName;
	private String password;
	private Integer age;
	/**
	 * 账号没有过期
	 */
	private boolean accountNonExpired;
	/**
	 * 账号没有锁定
	 */
	private boolean accountNonLocked;
	/**
	 * 密码没有过期
	 */
	private boolean credentialsNonExpired;
	/**
	 * 账号是否可用（没有被删除）
	 */
	private boolean enabled;
}
