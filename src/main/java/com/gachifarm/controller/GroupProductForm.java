package com.gachifarm.controller;

import java.io.Serializable;

import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;

@SuppressWarnings("serial")
public class GroupProductForm implements Serializable {
	private GroupProduct gProduct;
	private Product product;
	
	private boolean newGProduct;
	
	public GroupProductForm(GroupProduct gProduct, Product product) {
		this.gProduct = gProduct;
		this.product = product;
		this.newGProduct = false;
	}
	
	public GroupProductForm(Product product) {
		this.gProduct = new GroupProduct();
		this.product = product;
		gProduct.setProductId(product.getProductId());
		this.newGProduct = true;
	}
	
	public GroupProductForm() {
		
	}

	public GroupProduct getgProduct() {
		return gProduct;
	}

	public boolean isNewGProduct() {
		return newGProduct;
	}

	public Product getProduct() {
		return product;
	}


	
	
}
