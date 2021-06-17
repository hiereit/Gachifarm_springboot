package com.gachifarm.domain;

public class OrdersCommand {
	private String userName;
	private String phone;
	private String zip;
	private String addr1;
	private String addr2;
	private String cardType;
	private String creditNum;
	//data type 다시
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
	
}
