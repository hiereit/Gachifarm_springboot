package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.CartRepository;
@Controller
@SessionAttributes("userSession")
public class UpdateCartController {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductDao productDao;

	@RequestMapping("/cart/{product_id}/update")
	@ResponseBody
	public Cart updateCartProduct(@PathVariable("product_id") int productId, @RequestParam("type") String type) throws Exception {
		//인터셉터에 넣을 것 & 유저세션 param 추가
		//String userId = userSession.getAccount().getUserId();
		String userId = "DONGDUK01";
		CartPK cartId = new CartPK(userId, productId);
		CartProduct cartProduct = cartRepository.findCartProductByCartId(cartId);//해당 상품을 찾아옴s
		Product product = productDao.getProduct(productId);//그 상품의 정해진 양 가져옴
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
		cartProduct.calcInStock(product, cartQuantity);//수량 계산
		
		if (cartProduct.isInStock()) {
			cartProduct.setQuantity(cartQuantity);
			cartRepository.saveAndFlush(cartProduct);
			Cart cart = new Cart(productId, price, cartQuantity, cartQuantity * price);
			return cart;
		}
		else {
			return null;
		}
	}
	/*체크로 지우고 그러는 것 같은데 구현 가능 여부 불확실
	@RequestMapping("/cart/{product_id}/update/check")
	public String updateCartProductCheck(@ModelAttribute("userSession") UserSession userSession, @PathVariable int productId) throws Exception {
		String userId = userSession.getAccount().getUserId();
		CartPK cartId = new CartPK(userId, productId);
		cartRepository.deleteCartByCartId(cartId);
		return "Cart";
	}*/
}
