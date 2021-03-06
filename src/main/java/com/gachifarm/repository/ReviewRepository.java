package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer>{
	List<Review> findReviewByUserId(String userId);
	
	Page<Review> findAllByProductId(Pageable pageable, int productId);

	Review findBylineProductId(int lineProductId);
}