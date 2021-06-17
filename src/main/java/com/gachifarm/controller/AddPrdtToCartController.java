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
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.CartRepository;
import com.gachifarm.service.GachiFarmFacade;
@Controller
@SessionAttributes("userSession")
public class AddPrdtToCartController {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductDao productDao;
	//@Autowired
	//private AccountRepository accountRepository;

	/*@RequestMapping("/cart/{product_id}/add")
	public Page<CartProduct> addCartProduct(@ModelAttribute("userSession") UserSession userSession, @PathVariable int productId, @RequestParam("quantity") int cartQuantity, @RequestParam("page") int page) throws Exception {
		Product product = productDao.getProduct(productId);//해당 제품의 재고를 알기 위함
		String userId = userSession.getAccount().getUserId();
		//if (accountRepository.findById(userId) != null) {//user가 로그인이 된 상태면
			CartPK cartId = new CartPK(userId, productId);
			CartProduct cartProduct = new CartProduct(cartId, cartQuantity, product);//cart에 넣을 객체
			
			if (cartProduct.isInStock()) {
				PageRequest pageReq = PageRequest.of(page,5);
				cartRepository.saveAndFlush(cartProduct);
				return cartRepository.findAllByUserId(userId, pageReq);
			}
			else {
				//아예 페이지 못넘어가게 고려 & 트랜잭션
			}
		}
		else {
			
		}
		return null;
	}*/
	@RequestMapping("/cart/{product_id}/add")
	public ModelAndView addCartProduct(@PathVariable("product_id") int productId, @RequestParam("quantity") int cartQuantity) throws Exception {
		Product product = productDao.getProduct(productId);//해당 제품의 재고를 알기 위함
		String userId = "DONGDUK02";
		//if (accountRepository.findById(userId) != null) {//user가 로그인이 된 상태면
			CartPK cartId = new CartPK(userId, productId);
			CartProduct cartProduct = new CartProduct(cartId, cartQuantity, product);//cart에 넣을 객체
			
			if (cartProduct.isInStock()) {
				//PageRequest pageReq = PageRequest.of(page,5);
				cartRepository.saveAndFlush(cartProduct);
				List<CartProduct> cart = cartRepository.findByCartIdUserId(userId);
				return new ModelAndView("Cart", "cart", cart);
			}
			else {
				//아예 페이지 못넘어가게 고려 & 트랜잭션
			}
		return null;
	}
}
