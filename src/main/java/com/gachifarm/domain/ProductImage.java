package com.gachifarm.domain;

public class ProductImage {
	private int img_id;
	private int product_id;
	private String imgName;
	private String imgPath;
	
	public ProductImage(int img_id, int product_id, String imgName, String imgPath) {
		super();
		this.img_id = img_id;
		this.product_id = product_id;
		this.imgName = imgName;
		this.imgPath = imgPath;
	}
	
	public int getImg_id() {
		return img_id;
	}
	public void setImg_id(int img_id) {
		this.img_id = img_id;
	}
	
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	
	@Override
	public String toString() {
		return "ProductImage [img_id=" + img_id + ", product_id=" + product_id + ", imgName=" + imgName + ", imgPath="
				+ imgPath + "]";
	}	
}
