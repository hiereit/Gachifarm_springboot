package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gachifarm.domain.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Integer>{
	@Query(value="select imgpath from productimage where img_id = (SELECT img_id FROM ( SELECT img_id FROM productimage ORDER BY dbms_random.value ) WHERE rownum = 1)",
			nativeQuery = true)
	String getRandomImagPath();
}
