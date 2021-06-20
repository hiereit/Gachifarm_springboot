package com.gachifarm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.service.GachiFarmFacade;
@Controller
@SessionAttributes("account")
public class AddPrdtToCartController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}

	@RequestMapping("/cart/{product_id}/add")
	public String addCartProduct(@PathVariable("product_id") int productId, @RequestParam("quantity") int cartQuantity, HttpSession userSession) throws Exception {
		String userId = ((Account) userSession.getAttribute("account")).getUserId();
		CartPK cartId = new CartPK(userId, productId);
		CartProduct cartProduct = new CartProduct(cartId, cartQuantity);
		gachifarm.insertCart(cartProduct);
		return "redirect:/cart";
	}
}
