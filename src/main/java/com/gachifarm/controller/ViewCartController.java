package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
public class ViewCartController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}

	@RequestMapping("cart")
	public ModelAndView viewCart(HttpSession userSession) throws Exception {
		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();
		List<CartProduct> cartPrdt = gachifarm.findCartListByUserId(userId);
		List<Cart> cart = new ArrayList<Cart>();
		for (int i = 0; i < cartPrdt.size(); i++) {
			Product product = gachifarm.getProduct(cartPrdt.get(i).getCartId().getProductId());
			int productId = product.getProductId();
			String path;
			ProductImage img = gachifarm.getProductImageByPid(productId);
			if (img == null) {
				path = "/images/noImage.png";
			}
			else {
				path = img.getImgPath();
			}
			String productName = product.getPrdtName();
			int price = product.getPrice();
			int quantity = cartPrdt.get(i).getQuantity();
			int totalPrice = quantity * price;
			Cart c = new Cart(path, productId, productName, price, quantity, totalPrice);
			cart.add(c);
		}
		return new ModelAndView("OrderAndCart/Cart", "cart", cart);
	}
}
