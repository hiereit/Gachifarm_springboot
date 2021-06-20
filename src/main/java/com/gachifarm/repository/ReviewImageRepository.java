package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.ReviewImage;

public interface ReviewImageRepository extends JpaRepository<ReviewImage, Integer>{
	ReviewImage findByReviewId(int reviewId);
}
