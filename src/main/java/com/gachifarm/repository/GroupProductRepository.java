package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.GroupProduct;
public interface GroupProductRepository extends JpaRepository<GroupProduct, Integer>{

	List<GroupProduct> findGroupProductByUserId(String userId);
	
	GroupProduct findGroupProductBygProductId(int groupProudctId);
}
