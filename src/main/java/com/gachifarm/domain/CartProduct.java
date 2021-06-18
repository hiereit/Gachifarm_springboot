package com.gachifarm.domain;

import java.io.Serializable;

import javax.persistence.Column;
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
	@Column(name="stock_check")
	private boolean inStock;
	
	public CartProduct() {}
	public CartProduct(CartPK cartId, int quantity) {
		super();
		this.cartId = cartId;
		this.quantity = quantity;
		this.inStock = true;
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

	public boolean isInStock() {
		return inStock;
	}
	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	public void calcInStock(Product product, int quantity) {
		if(product.getQuantity() - quantity >= 0) {
			setInStock(true);
		}
		else {
			setInStock(false);
		}
	}
}
