package com.gachifarm.controller;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class StoreRegRequest {
	@NotEmpty
	@NotNull
	private String storeName;
	
	@Length(max=250)
	@NotEmpty
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
}
