package com.gachifarm.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Address implements Serializable{
	String addr1;
	String addr2;
	String zip;
	
	public Address() { }
	public Address(String addr1, String addr2, String zip) {
		super();
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.zip = zip;
	}
	
	public Address(Address address) {
		super();
		this.addr1 = address.addr1;
		this.addr2 = address.addr2;
		this.zip = address.zip;
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
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	@Override
	public String toString() {
		return "Address [addr1=" + addr1 + ", addr2=" + addr2 + ", zip=" + zip + "]";
	}
	
	
}
