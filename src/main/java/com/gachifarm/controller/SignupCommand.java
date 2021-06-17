package com.gachifarm.controller;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;



@SuppressWarnings("serial")
public class SignupCommand implements Serializable {

	@NotEmpty
	private String userId;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String passwordConfirm;
	
	@NotEmpty
	private String userName;
	
//	@Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
	private String phone;
	
	@NotEmpty @Email
	private String email;
	
//	private Address address;
//	@Pattern(regexp ="^\\d{5}", message="우편번호는 5자리 숫자입니다.")
	private String zip;
	private String addr1;
	private String addr2;
	
	
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
	public String getPasswordConfirm() {
		return passwordConfirm;
	}
	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
		isPasswordEqualToConfirmPassword();
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
//	public Address getAddress() {
//		return address;
//	}
//	public void setAddress(Address address) {
//		this.address = address;
//	}
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
	

	@AssertTrue(message = "비밀번호가 일치하지 않습니다s")
	public boolean isPasswordEqualToConfirmPassword() {
		System.out.println("비밀번호가 일치하지 않습니다");
		return password.equals(passwordConfirm);
	}
	
	
}
