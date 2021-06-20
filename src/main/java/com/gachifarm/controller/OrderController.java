package com.gachifarm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("account")
public class OrderController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	
	List<Cart> cart;
	String productTotal;
	String total;
	Account user;
	
	public String getImgPath(int productId) {
		ProductImage img = gachifarm.getProductImageByPid(productId);
		if (img == null) {
			return "/images/noImage.png";
		}
		else {
			return img.getImgPath();
		}
	}
	
	@PostMapping("/order/form")
	public ModelAndView addOrder(HttpServletRequest req, HttpSession userSession) throws Exception {
		String userId = ((Account) userSession.getAttribute("account")).getUserId();
		cart = new ArrayList<Cart>();
		String[] checkedArray = req.getParameter("checkedIdList").split(",");
		productTotal = req.getParameter("productTotal");
		total = req.getParameter("Total");
		List<String> checkedList = new ArrayList<String>();
		checkedList = Arrays.asList(checkedArray);
		ModelAndView mav = new ModelAndView("OrderAndCart/NewOrderForm");
		List<CartPK> cartIdList = new ArrayList<CartPK>();
		for (String order : checkedList) {
			cartIdList.add(new CartPK(userId, Integer.parseInt(order)));
		}
		List<CartProduct> cartProducts = gachifarm.findCartListByCartId(cartIdList);
		for (CartProduct cartProduct : cartProducts) {
			Product product = gachifarm.getProduct(cartProduct.getCartId().getProductId());
			int productId = product.getProductId();
			String path = getImgPath(productId);
			String productName = product.getPrdtName();
			int price = product.getPrice();
			int quantity = cartProduct.getQuantity();
			int totalPrice = quantity * price;
			Cart c = new Cart(path, productId, productName, price, quantity, totalPrice);
			cart.add(c);
		}
		user = gachifarm.findByUserId(userId);
		OrdersCommand order = new OrdersCommand(user.getUserName(), user.getPhone(), user.getZip(), user.getAddr1(), user.getAddr2());
		mav.addObject("order", order);
		mav.addObject("user", user);
		mav.addObject("cart", cart);
		mav.addObject("productTotal", productTotal);
		mav.addObject("Total", total);
		mav.addObject("chk", true);
		return mav;
	}
	
	@GetMapping("/order/form")
	public ModelAndView addOneOrder(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity, HttpSession userSession) throws Exception {
		String userId = ((Account) userSession.getAttribute("account")).getUserId();
		cart = new ArrayList<Cart>();
		ModelAndView mav = new ModelAndView("OrderAndCart/NewOrderForm");
		Product product = gachifarm.getProduct(productId);
		String path = getImgPath(productId);
		String productName = product.getPrdtName();
		int price = product.getPrice();
		int totalPrice = quantity * price;
		productTotal = String.valueOf(totalPrice);
		total = String.valueOf(totalPrice + 3000);
		Cart c = new Cart(path, productId, productName, price, quantity, totalPrice);
		cart.add(c);
		user = gachifarm.findByUserId(userId);
		OrdersCommand order = new OrdersCommand(user.getUserName(), user.getPhone(), user.getZip(), user.getAddr1(), user.getAddr2());
		mav.addObject("order", order);
		mav.addObject("user", user);
		mav.addObject("cart", cart);
		mav.addObject("productTotal", productTotal);
		mav.addObject("Total", total);
		mav.addObject("chk", true);
		return mav;
	}
	
	@RequestMapping("/main")
	public ModelAndView confirmForm() throws Exception {
		return new ModelAndView("Main");
	}
	
	@RequestMapping("/order/confirm")
	public ModelAndView confirmOrder(HttpServletRequest req, @Valid @ModelAttribute("order") OrdersCommand order, BindingResult result, HttpSession userSession) throws Exception {
		String userId = ((Account) userSession.getAttribute("account")).getUserId();
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("OrderAndCart/NewOrderForm");
			mav.addObject("chk", req.getParameter("frmCheck"));
			mav.addObject("user", user);
			mav.addObject("cart", cart);
			mav.addObject("productTotal", productTotal);
			mav.addObject("Total", total);
			return mav;
		}
		ModelAndView mav = new ModelAndView("OrderAndCart/ConfirmOrder");
		String username = order.getUserName();
		int totalPrice = Integer.parseInt(total);
		String phone = order.getPhone();
		Date orderDate = new Date(System.currentTimeMillis());
		String shipAddr1 = order.getAddr1();
		String shipAddr2 = order.getAddr2();
		String zipCode = order.getZip();
		String creditNum = order.getCreditNum();
		SimpleDateFormat sdf = new SimpleDateFormat("MMyy");
		Date expireDate = new Date(sdf.parse(order.getExpireDate()).getTime());
		String cardType = order.getCardType();
		String status = "주문 완료";
		Orders orders = new Orders(username, totalPrice, phone, orderDate, shipAddr1, shipAddr2,
				zipCode, creditNum, expireDate, cardType, status, userId);
		
		for (Cart lineProducts : cart) {
			int quantity = lineProducts.getQuantity();
			int productId = lineProducts.getProductId();
			Product product = gachifarm.getProduct(productId);
			if (product.getQuantity() - quantity < 0) {
				return mav;
			}
		}
		
		gachifarm.insertOrder(orders);
		gachifarm.changeOrderStatus(orders, orderDate);
		int orderId = orders.getOrderId();
		
		for (Cart lineProducts : cart) {
			int quantity = lineProducts.getQuantity();
			int price = lineProducts.getTotalPrice();
			int productId = lineProducts.getProductId();
			String productName = lineProducts.getProductName();
			Product product = gachifarm.getProduct(productId);
			product.setQuantity(product.getQuantity() - quantity);
			gachifarm.changeProductQty(product);
			LineProduct lineProduct = new LineProduct(orderId, quantity, price, productId, productName);
			gachifarm.insertLineProduct(lineProduct);
			List<CartPK> cartIdList = new ArrayList<CartPK>();
			cartIdList.add(new CartPK(userId, productId));
			gachifarm.deleteCart(cartIdList);
		}
		mav.addObject("orderId", orderId);
		mav.addObject("total", total);
		return mav;
	}
}
