package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="STORE")
public class Store implements Serializable{
	//필드 선언
	@Id
	@Column(name="user_id")
	private String userId;
	private String storeName;
	private String storeInfo;
	
	//기본 생성자
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Store(String userId, String storeName, String storeInfo) {
		super();
		this.userId = userId;
		this.storeName = storeName;
		this.storeInfo = storeInfo;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStoreInfo() {
		return storeInfo;
	}
	public void setStoreInfo(String storeInfo) {
		this.storeInfo = storeInfo;
	}

	@Override
	public String toString() {
		return "Store [userId=" + userId + ", storeName=" + storeName + ", storeInfo=" + storeInfo + "]";
	}
	
	
}


