package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.gachifarm.domain.GroupProduct;
public interface GroupProductRepository extends JpaRepository<GroupProduct, Integer>{

	List<GroupProduct> findGroupProductByUserId(String userId);
	
	GroupProduct findGroupProductBygProductId(int gProductId);
	
	
}
