package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.CartRepository;

@SessionAttributes("userSession")
public class RemovePrdtFromCartController {
	@Autowired
	private CartRepository cartRepository;

	@RequestMapping("/cart/{product_id}/delete")
	public Page<CartProduct> removeCartProduct(@ModelAttribute("userSession") UserSession userSession, @PathVariable int productId) throws Exception {
		String userId = userSession.getAccount().getUserId();
		CartPK cartId = new CartPK(userId, productId);
		cartRepository.deleteCartByCartId(cartId);
		PageRequest pageReq = PageRequest.of(0,5);
		return cartRepository.findAllByUserId(userId, pageReq);
	}
}
