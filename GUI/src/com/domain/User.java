package com.domain;

/**
 * domainʵ����� ÿ�ζ�ȡһ�м�¼ ÿһ�оͱ�ʾһ���� ÿ��������������
 * 
 * @author RLe����
 *
 */
public class User {

	private String account; 		//�û��˺�����
	private String password;		//�û���������

	
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
