package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

	List<Orders> findOrdersByUserId(String userId);
	
}
