package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{

	ProductImage findProductImageByProductId(int productId);
	
}
