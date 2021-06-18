package com.gachifarm.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LineProductPK implements Serializable {
	private int orderId;
	private int lineProductId;
	
	public LineProductPK() {}
	public LineProductPK(int orderId) {
		super();
		this.orderId = orderId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getLineProductId() {
		return lineProductId;
	}
	public void setLineProductId(int lineProductId) {
		this.lineProductId = lineProductId;
	}
}
