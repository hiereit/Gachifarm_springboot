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
@Table(name="PRODUCTIMAGE")
@SequenceGenerator(name = "PRDT_IMG_SEQ_GENERATOR", sequenceName = "PRDT_IMG_SEQ", initialValue = 1, allocationSize = 1)
public class ProductImage implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PRDT_IMG_SEQ_GENERATOR")
	@Column(name="img_id")
	private int imgId;
	private String imgName;
	private String imgPath;
	@Column(name="product_id")
	private int productId;
	
	public ProductImage() {
		super();
		// TODO Auto-generated constructor stub
	}	/*
	public ProductImage(int imgId, String imgName, String imgPath, int productId) {
		super();
		this.imgId = imgId;
		this.imgName = imgName;
		this.imgPath = imgPath;
		this.productId = productId;
	}

	*/
	public ProductImage(String imgName, String imgPath, int productId) {
		super();
		this.imgName = imgName;
		this.imgPath = imgPath;
		this.productId = productId;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
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
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	@Override
	public String toString() {
		return "ProductImage [imgId=" + imgId + ", imgName=" + imgName + ", imgPath=" + imgPath + ", productId="
				+ productId + "]";
	} 
}
