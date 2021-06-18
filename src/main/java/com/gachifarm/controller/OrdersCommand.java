package com.gachifarm.controller;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

public class OrdersCommand {
	@NotEmpty(message = "이름은 필수 항목입니다")
	private String userName;
	@Pattern(regexp = "^([0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9])$", message = "전화번호는 11자리 숫자입니다")
	private String phone;
	private String zip;
	private String addr1;
	@NotEmpty(message = "상세주소는 필수 항목입니다")
	private String addr2;
	private String cardType;
	@CreditCardNumber
	private String creditNum;
	
	@Pattern(regexp = "^(0[1-9]|1[0-2])([1-9][0-9])$", message = "날짜 형식은 월(MM)/년도(YY)입니다")
	private String expireDate;
	public OrdersCommand() {}
	public OrdersCommand(String userName, String phone, String zip, String addr1, String addr2, String cardType,
			String creditNum, String expireDate) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.zip = zip;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.cardType = cardType;
		this.creditNum = creditNum;
		this.expireDate = expireDate;
	}
	
	public OrdersCommand(@NotEmpty(message = "이름은 필수 항목입니다") String userName,
			@NotEmpty(message = "전화번호는 필수 항목입니다") String phone, String zip, String addr1,
			@NotEmpty(message = "상세주소는 필수 항목입니다") String addr2) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.zip = zip;
		this.addr1 = addr1;
		this.addr2 = addr2;
	}
	
	public OrdersCommand(String zip, String addr1, String cardType) {
		super();
		this.zip = zip;
		this.addr1 = addr1;
		this.cardType = cardType;
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
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	@Override
	public String toString() {
		return "OrdersCommand [userName=" + userName + ", phone=" + phone + ", zip=" + zip + ", addr1=" + addr1
				+ ", addr2=" + addr2 + ", cardType=" + cardType + ", creditNum=" + creditNum + ", expireDate="
				+ expireDate + "]";
	}
	
}
