package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.CartRepository;

@Controller
@SessionAttributes("userSession")
public class ViewCartController {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductDao productDao;
	//@Autowired
	//private AccountRepository accountRepository;

	@RequestMapping("cart")
	public ModelAndView viewCart() throws Exception {
		String userId = "DONGDUK01";//userSession.getAccount().getUserId();
		//인터셉터에 넣을 것 & 유저세션 param 추가
		List<CartProduct> cartPrdt = cartRepository.findByCartIdUserId(userId);
		List<Cart> cart = new ArrayList<Cart>();
		for (int i = 0; i < cartPrdt.size(); i++) {
			Product product = productDao.getProduct(cartPrdt.get(i).getCartId().getProductId());
			int productId = product.getProductId();
			String productName = product.getPrdtName();
			int price = product.getPrice();
			int quantity = cartPrdt.get(i).getQuantity();
			int totalPrice = quantity * price;
			Cart c = new Cart(productId, productName, price, quantity, totalPrice);
			cart.add(c);
		}//서비스에서 작동시킬 것
		return new ModelAndView("OrderAndCart/Cart", "cart", cart);
	}
}
