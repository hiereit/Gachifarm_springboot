package com.gachifarm.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Account;

public interface AccountRepository extends JpaRepository<Account, String>{
	List<Account> findByUserId(String userId);

	long countByUserId(String userId);
	
	long deleteByUserId(String userId);
}
