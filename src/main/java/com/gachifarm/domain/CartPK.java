package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
@Embeddable
public class CartPK implements Serializable {
	@Column(name = "user_id")
	private String userId;
	@Column(name = "product_id")
	private int productId;
	public CartPK(String userId, int productId) {
		super();
		this.userId = userId;
		this.productId = productId;
	}
	public CartPK() {
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
