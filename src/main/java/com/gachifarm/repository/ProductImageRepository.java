package com.gachifarm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{
	Optional<ProductImage> findByProductId(int productId);
}
