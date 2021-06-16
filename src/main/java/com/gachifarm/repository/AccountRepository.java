package com.gachifarm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gachifarm.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
//	List<Account> findByUserId(String userId);

	Account findByUserId(String userId);
	
	@Query("select a from Account a where user_id = :userId and password = :password")
	Account findAccount(String userId, String password);
	
	long countByUserId(String userId);
	
	long deleteByUserId(String userId);
}
