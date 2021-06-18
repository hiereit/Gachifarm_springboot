package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.LineProductPK;

public interface LineProductRepository  extends JpaRepository<LineProduct, LineProductPK> {

	//addLineProduct -> List 생성 -> orderForm에서 출력
	//save orderId -> save LineProduct -> findById(orderId)로 주문 목록 가져옴

	//write by taeyeon
	long countByOrderId(int orderId);
	
//	@Query("select lp from LineProduct lp where order_id = :orderId")
	LineProduct findTop1ProductNameByOrderId(int orderId);
}