package com.gachifarm.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Orders implements Serializable {
	@Id
	@Column(name = "order_id")
	private int orderId;
	private String username;
	private int totalPrice;
	private String phone;
	private Date orderDate;
	private String shipAddr1;
	private String shipAddr2;
	private String zipCode;
	private String creditNum;
	private Date expireDate;
	private String status;
	@Column(name = "user_id")
	private String userId;
	private List<LineProduct> lineProducts = new ArrayList<LineProduct>();

	public int getOrder_id() {
		return orderId;
	}
	public void setOrder_id(int order_id) {
		this.orderId = order_id;
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
	public String getUserId() {
		return userId;
	}
	public void setUser_id(String userId) {
		this.userId = userId;
	}
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	public void setLineProducts(List<LineProduct> lineProducts) { this.lineProducts = lineProducts; }
	public List<LineProduct> getLineProducts() { return lineProducts; }

	public void initOrder(Account account, Cart cart) {
		/*userName = account.getUserName();
	    orderDate = new Date();

	    shipAddress1 = account.getAddress1();
	    shipAddress2 = account.getAddress2();
	    zipCode = account.getZipCode();

	    totalPrice = cart.getSubTotal();

	    creditCard = "999 9999 9999 9999";
	    expiryDate = "12/03";
	    cardType = "Visa";
	    courier = "UPS";
	    locale = "CA";
	    status = "P";*/

		Iterator<CartProduct> i = cart.getAllCartProducts();
		while (i.hasNext()) {
			CartProduct cartProduct = (CartProduct) i.next();
			addLineProduct(cartProduct);
		}
	}

	public void addLineProduct(CartProduct cartProduct) {
		LineProduct lineProduct = new LineProduct(lineProducts.size() + 1, cartProduct);
		addLineProduct(lineProduct);
	}

	public void addLineProduct(LineProduct lineProduct) {
		lineProducts.add(lineProduct);
	}
}