package com.gachifarm.service;

import java.util.List;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;


public interface GachiFarmFacade {
	//Account
//	List<Account> findByUserId(String userId);
	
	Account findByUserId(String userId);
	
	Account findAccount(String userId, String password);
	
	long countByUserId(String userId);	
	
	void save(Account account);
	
	long deleteByUserId(String userId);
	
	List<Orders> findOrdersByUserId(String userId);
	long countByOrderId(int orderId);
	LineProduct findTop1ProductNameByOrderId(int orderId);
	
	List<GroupProduct> findGroupProductByUserId(String userId);
	
}
