package com.gachfarm.domain;

public class Store {
	//필드 선언
	private String user_id;
	private String storeName;
	private String storeInfo;
	
	//기본 생성자
	public Store() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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

	
	//toString()
	@Override
	public String toString() {
		return "Store [user_id=" + user_id + ", storeName=" + storeName + ", storeInfo=" + storeInfo + "]";
	}	
}
