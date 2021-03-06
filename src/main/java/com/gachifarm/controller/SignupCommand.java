package com.gachifarm.controller;

import java.io.Serializable;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;



@SuppressWarnings("serial")
public class SignupCommand implements Serializable {

	@NotEmpty @Pattern(regexp = "^[A-Za-z0-9+]*$", message = "영문과 숫자만 입력가능합니다")
	private String userId;
	
	@NotEmpty
	private String password;
	
	private String passwordConfirm;
	
	@NotEmpty @Pattern(regexp = "^[a-zA-Zㄱ-ㅎ가-힣]*$", message = "이름엔 숫자나 특수기호가 들어갈 수 없습니다.")
	private String userName;
	
	@Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다")
	private String phone;
	
	@NotEmpty @Email
	private String email;
	
	@Pattern(regexp ="^\\d{5}", message="우편번호는 5자리 숫자입니다.")
	private String zip;
	@NotEmpty
	private String addr1;
	@NotEmpty
	private String addr2;
	
	public SignupCommand() {}
	
	public SignupCommand(@NotEmpty String userId, @NotEmpty String password, @NotEmpty String userName,
			@Pattern(regexp = "[0-9]{10,11}", message = "10~11자리의 숫자만 입력가능합니다") String phone,
			@NotEmpty @Email String email, @Pattern(regexp = "^\\d{5}", message = "우편번호는 5자리 숫자입니다.") String zip,
			String addr1, String addr2) {
		super();
		this.userId = userId;
		this.password = password;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.zip = zip;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
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
	

	@AssertTrue(message = "비밀번호가 일치하지 않습니다")
	public boolean isPasswordEqualToConfirmPassword() {
		System.out.println("Command print문 - 비밀번호가 일치하지 않습니다");
		return password.equals(passwordConfirm);
	}
	
	
}
