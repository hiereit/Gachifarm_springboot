package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

import org.springframework.beans.support.PagedListHolder;

@SuppressWarnings("serial")
public class Cart implements Serializable {//cart는 보여주기 위한 수단으로 생각
	private final Map<Integer, CartProduct> productMap = Collections.synchronizedMap(new HashMap<Integer, CartProduct>());
	private final PagedListHolder<CartProduct> productList = new PagedListHolder<CartProduct>();
	public Map<Integer, CartProduct> getProductMap() {
		return productMap;
	}

	public PagedListHolder<CartProduct> getProductList() {
		return productList;
	}

	public Cart() {
		this.productList.setPageSize(5);
	}
	

	public Iterator<CartProduct> getAllCartProducts() { return productList.getSource().iterator(); }
	public PagedListHolder<CartProduct> getCartProductList() { return productList; }
	public int getNumberOfproducts() { return productList.getSource().size(); }

	public boolean containsproductId(int productId) {
		return productMap.containsKey(productId);
	}

	public void addProduct(CartProduct cartProduct) {
		if (cartProduct != null) {
			cartProduct = new CartProduct();
			cartProduct.setProduct(product);
			cartProduct.setQuantity(0);
			cartProduct.setInStock(isInStock);
			productMap.put(String.valueOf(product.getProduct_id()), cartProduct);
			productList.getSource().add(cartProduct);
		}
		cartProduct.incrementQuantity();
	}

	public Product removeProductById(String productId) {
		CartProduct cartProduct = productMap.remove(productId);
		if (cartProduct == null) {
			return null;
		}
		else {
			productList.getSource().remove(cartProduct);
			return cartProduct.getProduct();
		}
	}

	public void calcIsInstock(int cartQuantity) {
		if ((product.getQuantity() - cartQuantity)  >= 0) {
			setStock_check(true);
		}
		else {
			setStock_check(false);
		}
	}
	
	public void incrementQuantityByproductId(String productId) {
		CartProduct cartProduct = productMap.get(productId);
		cartProduct.incrementQuantity();
	}

	public void setQuantityByproductId(String productId, int quantity) {
		CartProduct cartProduct = productMap.get(productId);
		cartProduct.setQuantity(quantity);
	}

	public double getSubTotal() {
		double subTotal = 0;
		Iterator<CartProduct> products = getAllCartProducts();
		while (products.hasNext()) {
			CartProduct cartProduct = (CartProduct) products.next();
			Product product = cartProduct.getProduct();
			double listPrice = product.getPrice();
			int quantity = cartProduct.getQuantity();
			subTotal += listPrice * quantity;
		}
		return subTotal;
	}

}
