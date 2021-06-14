package com.gachifarm.service;

import java.util.List;

import com.gachifarm.domain.Account;


public interface GachiFarmFacade {
	//Account
	Account getAccount(String username);

	Account getAccount(String username, String password);

	void insertAccount(Account account);

	void updateAccount(Account account);

	List<String> getUsernameList();
	
	public void remove(Account account);
	
	
	
}
