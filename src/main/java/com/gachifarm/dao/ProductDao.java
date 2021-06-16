package com.gachifarm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;

import com.gachifarm.domain.Product;

public interface ProductDao {
	//product_id로 Product 조회
	Product getProduct(int prdt_id) throws DataAccessException;
	//상품명으로 물품조회
	Product getProductByName(String prdt_name) throws DataAccessException;
	//공급자로 물품조회
	List<Product> getProductBySupplier(String userId) throws DataAccessException;
	
	//모든 상품 조회
	//@Query(value = "select * from Product")
	List<Product> getAllProduct() throws DataAccessException;
	//saleType에 따른 모든 상품 조회
	List<Product> getAllProductByType(String saleType) throws DataAccessException;
	//storeName에 따른 모든 상품 조회
	List<Product> getAllProductByStore(String userId) throws DataAccessException;	
	//키워드에 해당하는 전체상품 조회
	List<Product> searchAllProdcutList(String keyword) throws DataAccessException;
	//카테고리에 해당하는 전체상품 조회
	List<Product> searchAllProdcutListByCategory(String category) throws DataAccessException;
	//스토어 이름에 해당하는 product 조회
	List<Product> getAllProductByStoreName(String storeName) throws DataAccessException;
	
	//물품삽입
	void insertProduct(Product product) throws DataAccessException;
	//물품수정
	void updateProduct(Product product) throws DataAccessException;
	//물품삭제
	void deleteProduct(Product product) throws DataAccessException;
}
