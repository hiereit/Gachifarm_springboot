package com.gachifarm.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Account;
import com.gachifarm.domain.Cart;
import com.gachifarm.domain.CartPK;
import com.gachifarm.domain.CartProduct;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.domain.OrdersCommand;
import com.gachifarm.domain.Product;
import com.gachifarm.repository.AccountRepository;
import com.gachifarm.repository.CartRepository;
import com.gachifarm.repository.LineProductRepository;
import com.gachifarm.repository.OrdersRepository;

@Controller
@SessionAttributes("userSession")
public class OrderController {
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AccountRepository accRepository;
	@Autowired
	private OrdersRepository ordersRepository;
	@Autowired
	private LineProductRepository lineProductRepository;
	@Autowired
	private ProductDao productDao;
	List<Cart> cart = new ArrayList<Cart>();
	String total;
	
	@RequestMapping("/order/form")
	public ModelAndView addOrder(HttpServletRequest req) throws Exception {
		//인터셉터에 넣을 것
		//String userId = userSession.getAccount().getUserId();
		total = req.getParameter("Total");
		String[] checkedArray = req.getParameter("checkedIdList").split(",");
		List<String> checkedList = new ArrayList<String>();
		checkedList = Arrays.asList(checkedArray);
		ModelAndView mav = new ModelAndView("OrderAndCart/NewOrderForm");
		String userId = "DONGDUK01";
		List<CartPK> cartIdList = new ArrayList<CartPK>();
		for (String order : checkedList) {
			cartIdList.add(new CartPK(userId, Integer.parseInt(order)));
		}
		List<CartProduct> cartProducts = cartRepository.findAllById(cartIdList);//카트에 담긴 상품 리스트
		for (CartProduct cartProduct : cartProducts) {
			Product product = productDao.getProduct(cartProduct.getCartId().getProductId());
			int productId = product.getProductId();
			String productName = product.getPrdtName();
			int price = product.getPrice();
			int quantity = cartProduct.getQuantity();
			int totalPrice = quantity * price;
			Cart c = new Cart(productId, productName, price, quantity, totalPrice);
			cart.add(c);
		}//서비스에서 작동시킬 것
		Account user = accRepository.findByUserId(userId);
		mav.addObject("user", user);
		mav.addObject("cart", cart);
		mav.addObject("productTotal", req.getParameter("productTotal"));
		mav.addObject("Total", total);
		return mav;
	}
	
	@ModelAttribute("order")
	public OrdersCommand formBacking() {
		return new OrdersCommand();
	}
	
	@RequestMapping("/order/confirm")
	public ModelAndView confirmOrder(@ModelAttribute("order") OrdersCommand order) throws Exception {
		//인터셉터에 넣을 것
		//String userId = userSession.getAccount().getUserId();
		ModelAndView mav = new ModelAndView("OrderAndCart/ConfirmOrder");
		String userId = "DONGDUK01";
		String username = order.getUserName();
		int totalPrice = Integer.parseInt(total);
		String phone = order.getPhone();
		Date orderDate = new Date(System.currentTimeMillis());
		String shipAddr1 = order.getAddr1();
		String shipAddr2 = order.getAddr2();
		String zipCode = order.getZip();
		String creditNum = order.getCreditNum();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/yy");
		Date expireDate = new Date(sdf.parse(order.getExpireDate()).getTime());
		String cardType = order.getCardType();
		String status = "배송 중";//스케줄러 사용 필요
		Orders orders = new Orders(username, totalPrice, phone, orderDate, shipAddr1, shipAddr2,
				zipCode, creditNum, expireDate, cardType, status, userId);
		ordersRepository.saveAndFlush(orders);
		int orderId = orders.getOrderId();
	
		for (Cart lineProducts : cart) {
			int quantity = lineProducts.getQuantity();
			int price = lineProducts.getTotalPrice();
			int productId = lineProducts.getProductId();
			String productName = lineProducts.getProductName();
			LineProduct lineProduct = new LineProduct(orderId, quantity, price, productId, productName);
			lineProductRepository.saveAndFlush(lineProduct);
		}
		mav.addObject("orderId", orderId);
		mav.addObject("total", total);
		return mav;
	}
}
