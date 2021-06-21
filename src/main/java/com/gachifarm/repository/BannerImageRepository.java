package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gachifarm.domain.BannerImage;

public interface BannerImageRepository extends JpaRepository<BannerImage, Integer>{
	@Query(value="select imgpath from bannerimage where img_id = (SELECT img_id FROM ( SELECT img_id FROM bannerimage ORDER BY dbms_random.value ) WHERE rownum = 1)",
			nativeQuery = true)
	String getRandomImagPath();
}
