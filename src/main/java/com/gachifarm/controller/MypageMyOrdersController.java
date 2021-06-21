package com.gachifarm.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Orders;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes({"userSession", "myorders"})
public class MypageMyOrdersController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping("/user/mypage/myorders")
	public String myorders(HttpSession session, Model model, HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");

		Account account = userSession.getAccount();
		List<Orders> orderList = gachiFarm.findOrdersByUserId(account.getUserId());

		String[] productNameByOrderId = new String[orderList.size()];
		long[] countByOrderId = new long[orderList.size()];

		if(orderList != null) {
			for(int i = 0; i < orderList.size(); i++) {
				int orderId = orderList.get(i).getOrderId();
				countByOrderId[i] = gachiFarm.countByOrderId(orderId);
				
				System.out.println(gachiFarm.findTop1ProductNameByOrderId(orderId));
				if(gachiFarm.findTop1ProductNameByOrderId(orderId) != null) {
					productNameByOrderId[i] = gachiFarm.findTop1ProductNameByOrderId(orderId).getProductName();
					System.out.println(productNameByOrderId[i]);
				}
			}

			model.addAttribute("productNamesArray", productNameByOrderId);
			model.addAttribute("countsArray", countByOrderId);
			model.addAttribute("orders", orderList);
		}
		
		return "Account/MypageMyOrders";
	}
	
}
