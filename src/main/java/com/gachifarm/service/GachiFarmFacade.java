package com.gachifarm.service;

import java.util.List;

import com.gachifarm.domain.Account;


public interface GachiFarmFacade {
	//Account
//	List<Account> findByUserId(String userId);
	
	Account findByUserId(String userId);
	
	Account findByUserIdAndPassword(String userId, String password);
	
	long countByUserId(String userId);
	
	long deleteByUserId(String userId);
	
	void save(Account account);
	
}
