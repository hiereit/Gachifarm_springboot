package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;

@SuppressWarnings("serial")
public class Account implements Serializable{
	@Id
	@Column(name="user_id")
	String userId;
	String password;
	String userName;
	String phone;
	String email;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
		return "Account [user_id=" + userId + ", password=" + password + ", userName=" + userName + ", phone=" + phone
				+ ", email=" + email + "]";
	}
	
	
}
