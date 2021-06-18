package com.gachifarm.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.repository.AccountRepository;
import com.gachifarm.repository.GroupProductRepository;
import com.gachifarm.repository.LineProductRepository;
import com.gachifarm.repository.OrdersRepository;

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
	
	public Account findAccount(String userId, String password){
		return accountRepository.findAccount(userId, password);
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
	
	
	@Autowired   
	private OrdersRepository ordersRepository;
	public List<Orders> findOrdersByUserId(String userId){
		return ordersRepository.findOrdersByUserId(userId);
	}
	
	@Autowired   
	private LineProductRepository lineProductRepository;
	public long countByOrderId(int orderId) {
		return lineProductRepository.countByOrderId(orderId);
	}
	public LineProduct findTop1ProductNameByOrderId(int orderId){
		return lineProductRepository.findTop1ProductNameByOrderId(orderId);
	}
	
	@Autowired
	private GroupProductRepository groupProductRepository;
	public List<GroupProduct> findGroupProductByUserId(String userId){
		return groupProductRepository.findGroupProductByUserId(userId);
	}

}
