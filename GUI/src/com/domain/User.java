package com.domain;

/**
 * domain实体对象 每次读取一行记录 每一行就表示一个类 每个类有两个属性
 * 
 * @author RLe·奶
 *
 */
public class User {

	private String account; 		//用户账号属性
	private String password;		//用户密码属性

	
	public User() {
		super();
	}

	public User(String account, String password) {
		this.account = account;
		this.password = password;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
