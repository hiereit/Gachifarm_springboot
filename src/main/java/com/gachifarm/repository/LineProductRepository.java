package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.LineProduct;

public interface LineProductRepository  extends JpaRepository<LineProduct, String> {
	List<LineProduct> findProductByOrderId(String orderId);
	
	//addLineProduct -> List 생성 -> orderForm에서 출력
	//save orderId -> save LineProduct -> findById(orderId)로 주문 목록 가져옴
}
