package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.GroupProduct;
public interface GroupProductRepository extends JpaRepository<GroupProduct, String>{

	List<GroupProduct> findGroupProductByUserId(String userId);
}
