package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="PRODUCT")
@SequenceGenerator(name = "PRDT_SEQ_GENERATOR", sequenceName = "PRDT_SEQ", initialValue = 20, allocationSize = 1)
public class Product implements Serializable{
	// name=식별자 생성기 이름, sequenceName=DB에 등록될 시퀀스이름, initialValue=최초시작하는 수, allocationSize=증가하는수)
	//필드 선언
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PRDT_SEQ_GENERATOR")
	@Column(name="product_id")
	private int productId;
	private int price;
	private String origin;
	private String supplier;
	private String unit;
	private char stock;
	private int quantity;
	private String description;
	@Column(name="user_id")
	private String userId;
	@Column(name="saletype")
	private String saleType;
	private String category;
	@Column(name="prdt_name")
	private String prdtName;
	//기본 생성자
	public Product() { //기본 생성자
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int productId, int price, String origin, String supplier, String unit, char stock, int quantity,
			String description, String userId, String saleType, String category, String prdtName) {
		super();
		this.productId = productId;
		this.price = price;
		this.origin = origin;
		this.supplier = supplier;
		this.unit = unit;
		this.stock = stock;
		this.quantity = quantity;
		this.description = description;
		this.userId = userId;
		this.saleType = saleType;
		this.category = category;
		this.prdtName = prdtName;
	}
	
	
	public Product(int price, String origin, String supplier, String unit, char stock, int quantity, String description,
			String userId, String saleType, String category, String prdtName) {
		super();
		this.price = price;
		this.origin = origin;
		this.supplier = supplier;
		this.unit = unit;
		this.stock = stock;
		this.quantity = quantity;
		this.description = description;
		this.userId = userId;
		this.saleType = saleType;
		this.category = category;
		this.prdtName = prdtName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
	}
	
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", price=" + price + ", origin=" + origin + ", supplier=" + supplier
				+ ", unit=" + unit + ", stock=" + stock + ", quantity=" + quantity + ", description=" + description
				+ ", userId=" + userId + ", saleType=" + saleType + ", category=" + category + ", prdtName=" + prdtName
				+ "]";
	}	
}
