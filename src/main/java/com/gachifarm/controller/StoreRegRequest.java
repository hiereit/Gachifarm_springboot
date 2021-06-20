package com.gachifarm.controller;

public class StoreRegRequest {

	@NotEmpty
	private String storeName;
	private String storeInfo;
	
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
		return "StoreRegRequest [storeName=" + storeName + ", storeInfo=" + storeInfo + "]";
	}
	
}
