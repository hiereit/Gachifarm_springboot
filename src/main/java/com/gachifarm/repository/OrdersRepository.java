package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Orders;

public interface OrdersRepository { //extends JpaRepository<Orders, String> {
//	//List<Orders> findOrdersByUserId(String userId);
//	Page<Orders> findAllByUserId(String userId, PageRequest pageReq);
//	void deleteOrderByOrderId(String orderId);
//	Orders findOrderByOrderId(String orderId);
//	//addLineProduct -> List 생성 -> orderForm에서 출력
//	//save orderId -> save LineProduct -> findById(orderId)로 주문 목록 가져옴
}
