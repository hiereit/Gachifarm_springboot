package com.gachifarm.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gachifarm.domain.GroupBuyer;

public interface GroupBuyersRepository extends JpaRepository<GroupBuyer, Integer> {
	List<GroupBuyer> findByGroupProductId(int groupProductId);
	
	List<GroupBuyer> findGroupBuyersByUserId(String userId);
	
	GroupBuyer findGroupBuyersByUserIdAndGroupProductId(String userId, int groupProductId);
	
	void delete(GroupBuyer groupBuyer);
	
	@Modifying
	@Query("update GroupBuyer set orderDate = :date where groupProductId = :gProductId")
	void updateCompleteGroup(@Param("date") Date date, @Param("gProductId") int gProductId);

	Page<GroupBuyer> findByGroupProductId(Pageable pageable, int groupProductId);
}
