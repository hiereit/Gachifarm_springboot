package com.gachifarm.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;

public interface CartRepository  extends JpaRepository<CartProduct, CartPK>{
	//List<CartProduct> findCartByUserId(String userId,<Pageabe);//cartProd인지 헷갈림
	Page<CartProduct> findByCartIdUserId(String userId, PageRequest pageReq);//페이저블????
	List<CartProduct> findByCartIdUserId(String userId);
	void deleteCartByCartId(CartPK cartId);
	CartProduct findCartProductByCartId(CartPK cartId);
}
