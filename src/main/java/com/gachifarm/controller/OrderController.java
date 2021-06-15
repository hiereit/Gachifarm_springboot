package com.gachifarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.repository.AccountRepository;
import com.gachifarm.repository.CartRepository;
import com.gachifarm.repository.OrdersRepository;

@SessionAttributes("userSession")
public class OrderController {
	@Autowired
	private OrdersRepository orderRepository;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private AccountRepository accountRepository;

	@RequestMapping("/order/form")
	public ModelAndView addOrder(@ModelAttribute("userSession") UserSession userSession, @PathVariable int productId, @RequestParam("cart") CartProduct cart, @RequestParam("page") int page) throws Exception {
		String userId = userSession.getAccount().getUserId();
		if (accountRepository.findById(userId) == null) {//user가 로그인이 안 된 상태면
			return null;//로그인 페이지로 옮겨져야함
		}
		CartPK cartId = new CartPK(userId, productId);
		CartProduct cartProduct = orderRepository.findCartProductByCartPK(cartId);//해당 상품을 찾아옴
		PageRequest pageReq = PageRequest.of(page,5);
		cartRepository.saveAndFlush(cartProduct);
		Page<CartProduct> cartPage = cartRepository.findAllByUserId(userId, pageReq);
		List<CartProduct> cart = cartPage.getContent();
		return new ModelAndView("Cart", "cart", cart);
	}
}
