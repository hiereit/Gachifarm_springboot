package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class LineProduct implements Serializable {
	@Column(name = "order_id")
	private int orderId;
	private int quantity;
	private int unitPrice;
	@Column(name = "product_id")
	private int productId;
	@Id
	@Column(name = "lineProduct_id")
	private int lineProductId;
	//linenum이랑 product객체 써야되는지
	public LineProduct() {}
//lineProductId를 써도 되는지, num을 써야하는지 의문, 나머지 필드들도 셋하는건지
	public LineProduct(int lineProductId, CartProduct cartProduct) {
		this.quantity = cartProduct.getQuantity();
		//this.unitPrice = cartProduct.getProduct().getPrice() * cartProduct.getProduct().getUnit();
		//this.productId = cartProduct.getProduct().getProduct_id();
	}

	public int getLineProductId() {
		return lineProductId;
	}

	public void setLineProductId(int lineProductId) {
		this.lineProductId = lineProductId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public int getProductId() { return productId; }
	public void setProductId(int productId) { this.productId = productId; }

	public double getUnitPrice() { return unitPrice; }
	public void setUnitPrice(int unitPrice) { this.unitPrice = unitPrice; }

	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getTotalPrice() {
		return this.unitPrice * this.quantity;
	}
}