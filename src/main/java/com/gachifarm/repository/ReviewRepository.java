package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, String>{
	List<Review> findReviewByUserId(String userId);
}
