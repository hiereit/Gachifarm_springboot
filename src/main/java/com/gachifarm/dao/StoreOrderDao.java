package com.gachifarm.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.domain.Product;

public interface StoreOrderDao {
	List<LineProduct> getLineProduct(int productId) throws DataAccessException;
	List<Orders> getStoreOrderProduct(int prdtId) throws DataAccessException;
	
	
}
