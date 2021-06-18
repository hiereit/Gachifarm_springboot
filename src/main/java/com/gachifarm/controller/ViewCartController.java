package com.gachifarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.CartRepository;

@Controller
@SessionAttributes("userSession")
public class ViewCartController {
	@Autowired
	private CartRepository cartRepository;
	//@Autowired
	//private AccountRepository accountRepository;

	@RequestMapping("cart")
	public ModelAndView viewCart() throws Exception {
		String userId = "DONGDUK01";//userSession.getAccount().getUserId();
		//if (accountRepository.findById(userId) == null) {//user가 로그인이 안 된 상태면
		//	return null;//로그인 페이지로 옮겨져야함
		//}
		//CartPK cartId = new CartPK(userId, productId);
		//CartProduct cartProduct = cartRepository.findCartProductByCartPK(cartId);//해당 상품을 찾아옴
		//PageRequest pageReq = PageRequest.of(0,5);
		//cartRepository.saveAndFlush(cartProduct);
		//Page<CartProduct> cartPage = cartRepository.findByCartIdUserId(userId, pageReq);
		List<CartProduct> cart = cartRepository.findByCartIdUserId(userId);
		return new ModelAndView("Cart", "cart", cart);
	}
}
