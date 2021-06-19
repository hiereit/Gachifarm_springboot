package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.GroupBuyer;

public interface GroupBuyersRepository extends JpaRepository<GroupBuyer, Integer> {
	List<GroupBuyer> findByGroupProudctId(int groupProductId);
	
	List<GroupBuyer> findGroupBuyersByUserId(String userId);
}
