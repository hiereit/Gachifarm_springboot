package com.gachifarm.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Cart implements Serializable {
	private String img;
	private int productId;
	private String productName;
	private int price;
	private int quantity;
	private int totalPrice;
	
	public Cart(String img, int productId, String productName, int price, int quantity, int totalPrice) {
		super();
		this.img = img;
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	
	public Cart(int productId, int price, int quantity, int totalPrice) {
		super();
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
	}
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
}
