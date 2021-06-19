package com.gachifarm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;
@Controller
@SessionAttributes("account")
public class UpdateCartController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	
	@RequestMapping("/cart/{product_id}/update")
	@ResponseBody
	public Cart updateCartProduct(@PathVariable("product_id") int productId, @RequestParam("type") String type, HttpSession userSession) throws Exception {
		String userId = ((Account) userSession.getAttribute("account")).getUserId();
		CartPK cartId = new CartPK(userId, productId);
		CartProduct cartProduct = gachifarm.findCart(cartId);
		Product product = gachifarm.getProduct(productId);
		int price = product.getPrice();
		int cartQuantity = cartProduct.getQuantity();
		if (type.equals("add")) {
			cartQuantity++;
		}
		else if (type.equals("sub")) {
			if (cartQuantity == 1) {
				return null;
			}
			cartQuantity--;
		}
		
		cartProduct.setQuantity(cartQuantity);
		gachifarm.updateCart(cartProduct);
		Cart cart = new Cart(productId, price, cartQuantity, cartQuantity * price);
		return cart;
	}
}
