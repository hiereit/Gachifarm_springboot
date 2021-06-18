package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.SequenceGenerator;

@SuppressWarnings("serial")
@Entity
@SequenceGenerator(name = "LINEPRODUCT_SEQ_GENERATOR", sequenceName = "LINEPRODUCT_SEQ", initialValue = 1, allocationSize = 1)
@IdClass(LineProductPK.class)
public class LineProduct implements Serializable {
	@Id
	@Column(name = "order_id")
	private int orderId;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "LINEPRODUCT_SEQ_GENERATOR")
	@Column(name = "lineProduct_id")
	private int lineProductId;
	private int quantity;
	private int totalPrice;
	@Column(name = "product_id")
	private int productId;
	private String productName;
	
	public LineProduct() {}

	public LineProduct(int orderId, int quantity, int totalPrice, int productId,
			String productName) {
		super();
		this.orderId = orderId;
		this.quantity = quantity;
		this.totalPrice = totalPrice;
		this.productId = productId;
		this.productName = productName;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "LineProduct [orderId=" + orderId + ", lineProductId=" + lineProductId + ", quantity=" + quantity
				+ ", totalPrice=" + totalPrice + ", productId=" + productId + ", productName=" + productName + "]";
	}
}