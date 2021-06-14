package com.gachifarm.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Address implements Serializable{
	String addr1;
	String addr2;
	String zipCode;
	
	public Address() { }
	public Address(String addr1, String addr2, String zipCode) {
		super();
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zipCode = zipCode;
	}
	
	public Address(Address address) {
		super();
		this.addr1 = address.addr1;
		this.addr2 = address.addr2;
		this.zipCode = address.zipCode;
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
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	@Override
	public String toString() {
		return "Address [addr1=" + addr1 + ", addr2=" + addr2 + ", zipCode=" + zipCode + "]";
	}
	
	
}
