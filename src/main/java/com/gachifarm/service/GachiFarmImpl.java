package com.gachifarm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gachifarm.dao.AccountDao;
import com.gachifarm.domain.Account;

@Service
@Transactional
public class GachiFarmImpl implements GachiFarmFacade {
	@Autowired 
	@Qualifier("AccountDao")
	private AccountDao accountDao;
	
	
	public Account getAccount(String username) {
		return accountDao.getAccount(username);
	}

	public Account getAccount(String username, String password) {
		return accountDao.getAccount(username, password);
	}

	public void insertAccount(Account account) {
		accountDao.insertAccount(account);
	}

	public void updateAccount(Account account) {
		accountDao.updateAccount(account);
	}

	public List<String> getUsernameList() {
		return accountDao.getUsernameList();
	}

	public void remove(Account account) {
		accountDao.remove(account);
	}
}
