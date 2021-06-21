package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class BannerImage implements Serializable{
	
	@Id
	@Column(name="img_id")
	private int imgId;
	private String imgPath;
	
	public BannerImage() {}
	public BannerImage(int imgId, String imgPath) {
		super();
		this.imgId = imgId;
		this.imgPath = imgPath;
	}
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}