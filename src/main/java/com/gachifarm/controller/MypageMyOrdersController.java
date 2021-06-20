package com.gachifarm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Orders;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes({"account", "myorders"})
public class MypageMyOrdersController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping("/user/mypage/myorders")
	public String myorders(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		List<Orders> orderList = gachiFarm.findOrdersByUserId(account.getUserId());
		System.out.println("myorders()-orderList: " + orderList);
		String[] productNameByOrderId = new String[orderList.size()];
		long[] countByOrderId = new long[orderList.size()];
		System.out.println("배열길이(orderId수):" + countByOrderId.length);

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
