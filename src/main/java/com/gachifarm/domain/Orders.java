package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@SequenceGenerator(name = "ORDER_SEQ_GENERATOR", sequenceName = "ORDER_SEQ", initialValue = 1, allocationSize = 1)
@Entity
public class Orders implements Serializable {
	private String username;
	private int totalPrice;
	private String phone;
	private Date orderDate;
	private String shipAddr1;
	private String shipAddr2;
	private String zipCode;
	private String creditNum;
	private Date expireDate;
	private String cardType;
	private String status;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "ORDER_SEQ_GENERATOR")
	@Column(name = "order_id")
	private int orderId;
	@Column(name = "user_id")
	private String userId;
	
	public Orders() {}
	
	public Orders(String username, int totalPrice, String phone, Date orderDate, String shipAddr1, String shipAddr2,
			String zipCode, String creditNum, Date expireDate, String cardType, String status, String userId) {
		super();
		this.username = username;
		this.totalPrice = totalPrice;
		this.phone = phone;
		this.orderDate = orderDate;
		this.shipAddr1 = shipAddr1;
		this.shipAddr2 = shipAddr2;
		this.zipCode = zipCode;
		this.creditNum = creditNum;
		this.expireDate = expireDate;
		this.cardType = cardType;
		this.status = status;
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getShipAddr1() {
		return shipAddr1;
	}
	public void setShipAddr1(String shipAddr1) {
		this.shipAddr1 = shipAddr1;
	}
	public String getShipAddr2() {
		return shipAddr2;
	}
	public void setShipAddr2(String shipAddr2) {
		this.shipAddr2 = shipAddr2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCreditNum() {
		return creditNum;
	}
	public void setCreditNum(String creditNum) {
		this.creditNum = creditNum;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}