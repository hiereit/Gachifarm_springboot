package com.gachifarm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gachifarm.domain.Cart;

public interface CartRepository  extends JpaRepository<Cart, String>{
	
}
