package com.gachifarm.service;

import java.util.List;

import com.gachifarm.domain.Account;


public interface GachiFarmFacade {
	//Account
	List<Account> findByUserId(String userId);

	long countByUserId(String userId);
	
	long deleteByUserId(String userId);
	
	
	
}
