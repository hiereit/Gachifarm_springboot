package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.CartRepository;
@Controller
@SessionAttributes("userSession")
public class AddPrdtToCartController {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductDao productDao;
	//@Autowired
	//private AccountRepository accountRepository;

	@RequestMapping("/cart/{product_id}/add")
	public String addCartProduct(@PathVariable("product_id") int productId, @RequestParam("quantity") int cartQuantity) throws Exception {
		Product selectedProduct = productDao.getProduct(productId);//해당 제품의 재고를 알기 위함
		String userId = "DONGDUK01";
		//인터셉터에 넣을 것 & 유저세션 param 추가
		CartPK cartId = new CartPK(userId, productId);
		CartProduct cartProduct = new CartProduct(cartId, cartQuantity, selectedProduct);//cart에 넣을 객체

		if (cartProduct.isInStock()) {
			cartRepository.saveAndFlush(cartProduct);
			return "redirect:/cart";
		}
		else {
			return null;
			//아예 페이지 못넘어가게 고려 & 트랜잭션
		}
	}
}
