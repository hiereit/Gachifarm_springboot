package com.gachifarm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.repository.AccountRepository;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("groupProduct")
public class GroupAttendFormController {
	@Autowired
	private GachiFarmFacade gachiFarm;

	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@Autowired
	AccountRepository accountRepository;

	@RequestMapping("/group/product/attendForm")
	public String newGroupProductAttendForm(@RequestParam("gProductId") int gProductId, HttpSession session,
			@RequestParam("qty") int qty, Model model) {
		// UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request,
		// "userSession");
		/*
		 * if (userSession == null) { return "로그인페이지"; }
		 */
		GroupProduct gProduct = gachiFarm.getGroupProduct(gProductId);
		int price = (int) Math.ceil(gProduct.getProduct().getPrice() * 0.9);
		model.addAttribute("price", price);
		model.addAttribute("qty", qty);
		model.addAttribute("groupProduct", gProduct);
		model.addAttribute("user", accountRepository.getById("DONGDUK02"));
		model.addAttribute("order", new OrdersCommand());
		return "Group/GroupAttendForm";
	}

	@RequestMapping("/group/product/attend")
	public String attend(@ModelAttribute("order") OrdersCommand order, HttpSession session, HttpServletRequest request,
			Model model) throws ParseException {
		// user session 이용
		// 재고확인
		Account account = accountRepository.getById("DONGDUK02");
		GroupBuyer groupBuyer = new GroupBuyer();
		groupBuyer.setUserName(account.getUserName());
		groupBuyer.setUserId(account.getUserId());
		groupBuyer.setPhone(account.getPhone());
		groupBuyer.setTotalPrice(Integer.parseInt(request.getParameter("totalPrice")));
		groupBuyer.setCreditNum(order.getCreditNum());
		SimpleDateFormat format = new SimpleDateFormat("MM/yy");
		groupBuyer.setExpireDate(format.parse(order.getExpireDate()));
		groupBuyer.setCardType(order.getCardType());
		groupBuyer.setQty(Integer.parseInt(request.getParameter("qty")));
		groupBuyer.setAttendDate(new Date());
		GroupProduct gProduct = (GroupProduct) session.getAttribute("groupProduct");
		groupBuyer.setGroupProudctId(gProduct.getgProductId());
		gProduct.setCurrQty(gProduct.getCurrQty() + 1);
		gachiFarm.updateGroupProduct(gProduct);
		groupBuyer = gachiFarm.insertGroupBuyer(groupBuyer);
		model.addAttribute("groupBuyer", groupBuyer);
		model.addAttribute("result", true);
		return "Group/ResultGroupAttend";
	}
}
