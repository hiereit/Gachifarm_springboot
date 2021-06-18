package com.gachifarm.controller;

import java.io.Serializable;

import com.gachifarm.domain.Product;

@SuppressWarnings("serial")
public class ProductForm implements Serializable{
	private Product product;
	private boolean newProduct;
	
	public ProductForm(Product product, boolean newProduct) {
		super();
		this.product = product;
		this.newProduct = newProduct;
	}

	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	public boolean isNewProduct() {
		return newProduct;
	}
	public void setNewProduct(boolean newProduct) {
		this.newProduct = newProduct;
	}
	
}
