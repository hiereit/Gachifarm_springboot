package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, String>{

}
