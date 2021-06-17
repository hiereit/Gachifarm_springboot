package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupBuyerPK;

public interface GroupBuyersRepository extends JpaRepository<GroupBuyer, GroupBuyerPK> {
	List<GroupBuyer> findByGroupBuyerIdGroupProductId(int groupProductId);
}
