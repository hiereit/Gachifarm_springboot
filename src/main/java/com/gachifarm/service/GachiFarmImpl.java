package com.gachifarm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachifarm.domain.Account;
import com.gachifarm.repository.AccountRepository;

@Service
@Transactional
public class GachiFarmImpl implements GachiFarmFacade {
	@Autowired   
	private AccountRepository accountRepository;

//	public List<Account> findByUserId(String userId) {
//		return accountRepository.findByUserId(userId);
//	}
	
	public Account findByUserId(String userId) {
		return accountRepository.findByUserId(userId);
	}
	
	public Account findByUserIdAndPassword(String userId, String password){
		return accountRepository.findByUserIdAndPassword(userId, password);
	}

	public long countByUserId(String userId) {
		return accountRepository.countByUserId(userId);
	}

	public long deleteByUserId(String userId) {
		return accountRepository.deleteByUserId(userId) ;
	}
	
	public void save(Account account) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!Impl: " + account);
		accountRepository.save(account);
	}
	
}
