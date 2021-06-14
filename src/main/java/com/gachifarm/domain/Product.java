package com.gachifarm.domain;

public class Product {
	//필드 선언
	private String user_id;
	private String prdt_name;
	private int price;
	private String origin;
	private String supplier;
	private String unit;
	private char stock;
	private int quatity;
	private String description;
	private String saleType;
	private String category;

	//기본 생성자
	public Product() { //기본 생성자
		super();
		// TODO Auto-generated constructor stub
	}
	
	//getter setter
	public String getPrdt_name() {
		return prdt_name;
	}

	public void setPrdt_name(String prdt_name) {
		this.prdt_name = prdt_name;
	}
	public String getUser_id() {
		return user_id;
	}
	

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	
	public char getStock() {
		return stock;
	}
	public void setStock(char stock) {
		this.stock = stock;
	}
	
	public int getQuatity() {
		return quatity;
	}
	public void setQuatity(int quatity) {
		this.quatity = quatity;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getSaleType() {
		return saleType;
	}
	public void setSaleType(String saleType) {
		this.saleType = saleType;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	
	//toString()
	@Override
	public String toString() {
		return "Product [user_id=" + user_id + ", price=" + price + ", origin=" + origin + ", supplier=" + supplier
				+ ", unit=" + unit + ", stock=" + stock + ", quatity=" + quatity + ", description=" + description
				+ ", saleType=" + saleType + ", category=" + category + "]";
	}
}
