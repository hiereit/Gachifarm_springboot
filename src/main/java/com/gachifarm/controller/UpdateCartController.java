package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.CartRepository;
@SessionAttributes("userSession")
public class UpdateCartController {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private ProductDao productDao;

	@RequestMapping("/cart/{product_id}/update/quantity")
	public Page<CartProduct> addCartProduct(@ModelAttribute("userSession") UserSession userSession, @PathVariable int productId, @RequestParam("quantity") int cartQuantity, @RequestParam("page") int page) throws Exception {
		String userId = userSession.getAccount().getUserId();
		CartPK cartId = new CartPK(userId, productId);
		CartProduct cartProduct = cartRepository.findCartProductByCartPK(cartId);//해당 상품을 찾아옴
		
		Product product = productDao.getProduct(productId);//그 상품의 정해진 양 가져옴
		cartProduct.calcInStock(product, cartQuantity);//수량 계산
		
		if (cartProduct.isInStock()) {
			PageRequest pageReq = PageRequest.of(page,5);
			cartRepository.saveAndFlush(cartProduct);
			return cartRepository.findAllByUserId(userId, pageReq);
		}
		else {
			//아예 페이지 못넘어가게 고려 & 트랜잭션
		}
		return null;
	}
	/*이해 안됨
	@RequestMapping("/cart/{product_id}/update/check")
	public String updateCartProductCheck(@ModelAttribute("userSession") UserSession userSession, @PathVariable int productId) throws Exception {
		String userId = userSession.getAccount().getUserId();
		CartPK cartId = new CartPK(userId, productId);
		cartRepository.deleteCartByCartId(cartId);
		return "Cart";
	}*/
}
