package com.gachifarm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;

public interface CartRepository  extends JpaRepository<CartProduct, CartPK>{
	List<CartProduct> findByCartIdUserId(String userId);
	void deleteCartByCartId(CartPK cartId);
	CartProduct findCartProductByCartId(CartPK cartId);
}
