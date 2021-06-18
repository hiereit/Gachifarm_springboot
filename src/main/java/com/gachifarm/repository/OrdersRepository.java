package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gachifarm.domain.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Integer> {

  List<Orders> findOrdersByUserId(String userId);

}