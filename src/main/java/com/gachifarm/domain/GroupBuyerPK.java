package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class GroupBuyerPK implements Serializable {
	@Column(name="product_id")
	private int productId;
	@Column(name="gProduct_id")
	private int groupProductId;
	@Column(name="user_id")
	private String userId;
	
	public GroupBuyerPK(int productId, int groupProductId, String userId) {
		super();
		this.productId = productId;
		this.groupProductId = groupProductId;
		this.userId = userId;
	}

	public GroupBuyerPK() {}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getgroupProductId() {
		return groupProductId;
	}
	public void setgroupProductId(int groupProductId) {
		this.groupProductId = groupProductId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
}
