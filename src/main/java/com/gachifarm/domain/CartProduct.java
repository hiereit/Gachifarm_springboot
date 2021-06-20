package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "Cart")
public class CartProduct implements Serializable {
	@EmbeddedId
	private CartPK cartId;
	private int quantity;
	
	public CartProduct() {}
	public CartProduct(CartPK cartId, int quantity) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
	}

	public CartPK getCartId() {
		return cartId;
	}

	public void setCartId(CartPK cartId) {
		this.cartId = cartId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
