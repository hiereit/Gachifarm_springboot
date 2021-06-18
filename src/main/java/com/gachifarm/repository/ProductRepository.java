package com.gachifarm.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gachifarm.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	Page<Product> findAll(Pageable pageable);
	//Page<Product> getProductListbyPage(Pageable pageable, int pageNo);
	Page<Product> findByPrdtNameLike(String prdtName,Pageable pageable);
	
	Page<Product> findByUserId(String userId, Pageable pageable);
}

