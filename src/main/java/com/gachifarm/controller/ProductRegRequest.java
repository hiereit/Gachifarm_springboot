package com.gachifarm.controller;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class ProductRegRequest {
	private String productImg;
	private String imgPath;

	@NotEmpty
	@Length(max=30)
	private String prdtName;
	
	@Min(1)
	@Digits(integer=6 ,fraction=0)
	private int price;

	@NotEmpty
	@Length(max=25)
	private String origin;

	@NotEmpty
	@Length(max=10)
	private String supplier;

	@NotEmpty
	@Length(max=40)
	private String unit;
	
	@Min(1)
	@Digits(integer=4 ,fraction=0)
	private int quantity;
	
	@NotEmpty
	private String category;
	
	@NotEmpty
	@Length(max=1000)
	private String description;
	
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getPrdtName() {
		return prdtName;
	}
	public void setPrdtName(String prdtName) {
		this.prdtName = prdtName;
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
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "ProductRegRequest [productImg=" + productImg + ", imgPath=" + imgPath + ", prdtName=" + prdtName
				+ ", price=" + price + ", origin=" + origin + ", supplier=" + supplier + ", unit=" + unit
				+ ", quantity=" + quantity + ", category=" + category + ", description=" + description + "]";
	}
	
	
}
