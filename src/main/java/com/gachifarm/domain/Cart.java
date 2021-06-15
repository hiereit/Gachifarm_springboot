package com.gachifarm.domain;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.beans.support.PagedListHolder;

@SuppressWarnings("serial")
@Entity
public class Cart implements Serializable {
	@Id
	@Column(name = "user_id")
	private String userId;
	@Column(name = "product_id")
	private int productId;
	private int quantity;
	private boolean stock_check;
	private final Map<String, CartProduct> productMap = Collections.synchronizedMap(new HashMap<String, CartProduct>());
	private final PagedListHolder<CartProduct> productList = new PagedListHolder<CartProduct>();
	
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isStock_check() {
		return stock_check;
	}

	public void setStock_check(boolean stock_check) {
		this.stock_check = stock_check;
	}

	public Map<String, CartProduct> getProductMap() {
		return productMap;
	}

	public PagedListHolder<CartProduct> getProductList() {
		return productList;
	}

	public Cart() {
		this.productList.setPageSize(4);
	}

	public Iterator<CartProduct> getAllCartProducts() { return productList.getSource().iterator(); }
	public PagedListHolder<CartProduct> getCartProductList() { return productList; }
	public int getNumberOfproducts() { return productList.getSource().size(); }

	public boolean containsproductId(String productId) {
		return productMap.containsKey(productId);
	}

	public void addproduct(Product product, boolean isInStock) {
		CartProduct cartProduct = productMap.get(product.getPrdt_name());
		if (cartProduct == null) {
			cartProduct = new CartProduct();
			cartProduct.setProduct(product);
			cartProduct.setQuantity(0);
			cartProduct.setInStock(isInStock);
			//productMap.put(String.valueOf(product.getProduct_id()), cartProduct);
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
