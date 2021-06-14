package com.gachifarm.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Account implements Serializable{
	String user_id;
	String password;
	String userName;
	String phone;
	String email;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "Account [user_id=" + user_id + ", password=" + password + ", userName=" + userName + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	
	
}
