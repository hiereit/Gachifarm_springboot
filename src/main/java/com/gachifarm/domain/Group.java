package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="GROUPPRODUCT")
public class Group implements Serializable {
	@Id
	@Column(name="product_id")
	private int productId;
	
	private int minQty;
	private int currQty;
	private int limitQty;
	private Date period;
	private String recvPlace;
	private Date recvDate;
	private String location;
	@Column(name="gProduct_id")
	private int gProductId;
	private String status;
	public int getMinQty() {
		return minQty;
	}
	public void setMinQty(int minQty) {
		this.minQty = minQty;
	}
	public int getCurrQty() {
		return currQty;
	}
	public void setCurrQty(int currQty) {
		this.currQty = currQty;
	}
	public int getLimitQty() {
		return limitQty;
	}
	public void setLimitQty(int limitQty) {
		this.limitQty = limitQty;
	}
	public Date getPeriod() {
		return period;
	}
	public void setPeriod(Date period) {
		this.period = period;
	}
	public String getRecvPlace() {
		return recvPlace;
	}
	public void setRecvPlace(String recvPlace) {
		this.recvPlace = recvPlace;
	}
	public Date getRecvDate() {
		return recvDate;
	}
	public void setRecvDate(Date recvDate) {
		this.recvDate = recvDate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getgProductId() {
		return gProductId;
	}
	public void setgProductId(int gProductId) {
		this.gProductId = gProductId;
	}
	
	
	
}
