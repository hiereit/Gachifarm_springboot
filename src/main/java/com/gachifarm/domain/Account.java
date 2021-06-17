package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.*;

@SuppressWarnings("serial")
@Entity
public class Account implements Serializable{
	@Id
	@Column(name="user_id")
	@NotNull
	String userId;
	
	@NotNull
	String password;
	
	@NotNull
	String userName;
	
	String phone;
	
	@NotNull
	String email;
	
//	@Embedded
//	Address address;
	
	String zip;
	String addr1;
	String addr2;
	
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
	
	
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}


	
//	public Address getAddress() {
//		return address;
//	}
//
//	public void setAddress(Address address) {
//		this.address = address;
//	}
	
//	@Override
//	public String toString() {
//		return "Account [userId=" + userId + ", password=" + password + ", userName=" + userName + ", phone=" + phone
//				+ ", email=" + email + ", address=" + address + "]";
//	}

		@Override
	public String toString() {
		return "Account [userId=" + userId + ", password=" + password + ", userName=" + userName + ", phone=" + phone
				+ ", email=" + email + ", zip=" + zip + ", addr1=" + addr1 + ", addr2=" + addr2 + "]";
	}
	
}
