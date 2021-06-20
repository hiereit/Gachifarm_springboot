package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.LineProductPK;

public interface LineProductRepository  extends JpaRepository<LineProduct, LineProductPK> {

	long countByOrderId(int orderId);
	
	LineProduct findTop1ProductNameByOrderId(int orderId);
	
	LineProduct findByLineProductId(int lineProductId);

	List<LineProduct> findAllByOrderId(int orderId);

}