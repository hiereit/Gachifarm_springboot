package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.gachifarm.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
	Page<Product> findAll(Pageable pageable);
	//Page<Product> getProductListbyPage(Pageable pageable, int pageNo);
	Page<Product> findByPrdtNameContaining(String prdtName,Pageable pageable);
	
	Page<Product> findByUserId(String userId, Pageable pageable);
	
	@Query(value="select product_id from (select lp.product_id, count(lp.product_id) from Product p, LineProduct lp where p.product_id = lp.product_id group by lp.product_id order by count(lp.product_id) desc) where rownum < 5",
			nativeQuery = true)
	List<Integer> getBestProductIds();
	
	@Query(value="select * from(select product_id from PRODUCT order by product_id desc) where rownum < 5",
			nativeQuery = true)
	List<Integer> getNewProductIds();
}