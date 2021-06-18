package com.gachifarm.dao;

import org.springframework.dao.DataAccessException;

import com.gachifarm.domain.ProductImage;

public interface ProductImageDao {
	//product_id로 ProductImage조회
	ProductImage getProductImage(int prdt_id) throws DataAccessException;
	
	void insertProductImage(ProductImage product) throws DataAccessException;
	
	void updateProductImage(ProductImage product) throws DataAccessException;
	
	void deleteProductImage(ProductImage product) throws DataAccessException;

	ProductImage getProductImageByPid(int prdt_id) throws DataAccessException;
}
