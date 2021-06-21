package com.gachifarm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.gachifarm.domain.Review;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
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
	int orderId;
	
	@RequestMapping("/order/form")
	public ModelAndView addOrder(HttpServletRequest req, HttpSession userSession) throws Exception {
		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();
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
			String path = gachifarm.getImgPath(productId);
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
	
	@RequestMapping("/order/onePrdt/form")
	public ModelAndView addOneOrder(@RequestParam("productId") int productId, @RequestParam("quantity") int quantity, HttpSession userSession) throws Exception {
		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();
		cart = new ArrayList<Cart>();
		ModelAndView mav = new ModelAndView("OrderAndCart/NewOrderForm");
		Product product = gachifarm.getProduct(productId);
		String path = gachifarm.getImgPath(productId);
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
	
	@GetMapping("/order/confirm")
	public String confirmOrder2(Model model) throws Exception {
		model.addAttribute("orderId", orderId);
		model.addAttribute("total", total);
		return "OrderAndCart/ConfirmOrder";
	}
	
	@PostMapping("/order/confirm")
	public ModelAndView confirmOrder(HttpServletRequest req, @Valid @ModelAttribute("order") OrdersCommand order, BindingResult result, HttpSession userSession) throws Exception {
		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();
		if (result.hasErrors()) {
			ModelAndView mav = new ModelAndView("OrderAndCart/NewOrderForm");
			mav.addObject("chk", req.getParameter("frmCheck"));
			mav.addObject("user", user);
			mav.addObject("cart", cart);
			mav.addObject("productTotal", productTotal);
			mav.addObject("Total", total);
			return mav;
		}
		ModelAndView mav = new ModelAndView("redirect:/order/confirm");
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
				mav.addObject("orderId", null);
				return mav;
			}
		}
		gachifarm.insertOrder(orders);
		gachifarm.changeOrderStatus(orders, orderDate);
		orderId = orders.getOrderId();
		
		if(cart.size() == 1) {
			Cart oneLineProduct = cart.get(0);
			int quantity = oneLineProduct.getQuantity();
			int price = oneLineProduct.getTotalPrice();
			int productId = oneLineProduct.getProductId();
			String productName = oneLineProduct.getProductName();
			Product product = gachifarm.getProduct(productId);
			product.setQuantity(product.getQuantity() - quantity);
			gachifarm.changeProductQty(product);
			LineProduct lineProduct = new LineProduct(orderId, quantity, price, productId, productName);
			gachifarm.insertLineProduct(lineProduct);
			mav.addObject("orderId", orderId);
			mav.addObject("total", total);
			return mav;
		}
		
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
	
	@RequestMapping("/order/{order_id}/detail")
	public ModelAndView detailOrder(@PathVariable("order_id") int orderId, HttpSession userSession) throws Exception {
		ModelAndView mav = new ModelAndView("OrderAndCart/OrderDetail");
		Orders order = gachifarm.findOrder(orderId);
		Account user = gachifarm.findByUserId(order.getUserId());
		String email = user.getEmail();
		List<LineProduct> orderProducts = gachifarm.findLineProducts(orderId);
		List<Cart> lineProducts = new ArrayList<Cart>();
		HashMap<Integer, Integer> reviewMap1 = new HashMap<>();
		HashMap<Integer, Integer> reviewMap2 = new HashMap<>();
		for (LineProduct line : orderProducts) {
			Product product = gachifarm.getProduct(line.getProductId());
			int productId = product.getProductId();
			String path = gachifarm.getImgPath(product.getProductId());
			String productName = product.getPrdtName();
			int price = product.getPrice();
			int quantity = line.getQuantity();
			int totalPrice = line.getTotalPrice();
			Cart cart = new Cart(path, productId, productName, price, quantity, totalPrice);
			lineProducts.add(cart);
			Review review = gachifarm.findReview(line.getLineProductId());
			if (review == null) {
				reviewMap1.put(productId, line.getLineProductId());
			}
			else {
				reviewMap2.put(productId, review.getReviewId());
			}
		}
		mav.addObject("order", order);
		mav.addObject("email", email);
		mav.addObject("lineProducts", lineProducts);
		mav.addObject("review1", reviewMap1);
		mav.addObject("review2", reviewMap2);
		mav.addObject("productTotal", (order.getTotalPrice() - 3000));
		return mav;
	}
}
