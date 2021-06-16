package com.gachifarm.service;

import java.util.List;

import com.gachifarm.domain.Product;
import com.gachifarm.domain.Store;


public interface GachiFarmFacade {
	//Account
//	List<Account> findByUserId(String userId);

//
//	
//	long deleteByUserId(String userId);

	//-------------------------------------------------------------------------
	// Product 관련 메소드
	//-------------------------------------------------------------------------
	void insertProduct(Product product);

	
	Account findByUserId(String userId);
	
	Account findAccount(String userId, String password);
	
	long countByUserId(String userId);	
	
	Product getProduct(int prdt_id);
	
	Product getProductByName(String prdt_name);
	
	List<Product> getProductBySupplier(String supplier);
	
	List<Product> getAllProduct();
	
	List<Product> getAllProductByType(String saleType);
	
	List<Product> getAllProductByStore(String userId);
	//List<Product> searchAllProdcutListByCategory(String category);
	
	List<Product> getAllProductByStoreName(String storeName);
	
	List<Product> searchAllProdcutList(String keyword);
	
	List<Product> searchAllProdcutListByCategory(String category);
	
	//-------------------------------------------------------------------------
	// Store 관련 메소드
	//-------------------------------------------------------------------------
	void insertStore(Store store);
	
	Store getStore(String userId);
	
	Store getStoreName(String storename);
	
	List<Store> getAllStore();
	
	void save(Account account);
	
}
