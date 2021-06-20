package com.gachifarm.controller;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
public class GroupAttendFormController {
	@Autowired
	private GachiFarmFacade gachiFarm;

	@RequestMapping("/group/product/attendForm")
	public String newGroupProductAttendForm(@RequestParam("gProductId") int gProductId, HttpSession session,
			@RequestParam("qty") int qty, Model model) {
		GroupProduct gProduct = gachiFarm.getGroupProduct(gProductId);
		Account account =((UserSession) session.getAttribute("userSession")).getAccount();
		int price = (int) Math.ceil(gProduct.getProduct().getPrice() * 0.9);
		gProduct.setFilePath(gachiFarm.getProductImageByPid(gProduct.getProductId()).getImgPath());
		model.addAttribute("price", price);
		model.addAttribute("groupProduct", gProduct);
		model.addAttribute("user", account);
		GroupBuyer gBuyer = new GroupBuyer();
		gBuyer.setQty(qty);
		gBuyer.setTotalPrice(price*qty);
		model.addAttribute("order", gBuyer);
		return "Group/GroupAttendForm";
	}
	
	@GetMapping("/group/product/attend")
	public String attendCom() {
		return "redirect:/main";
	}

	@PostMapping("/group/product/attend")
	public String attend(@ModelAttribute("order") @Valid GroupBuyer gBuyer, BindingResult result, HttpSession session, HttpServletRequest request,
			Model model) throws ParseException {
		boolean resultAtt = true;
		int gProductId = Integer.parseInt(request.getParameter("gProductId"));
		Account account =((UserSession) session.getAttribute("userSession")).getAccount();
		GroupProduct gProduct = gachiFarm.getGroupProduct(gProductId);
		if (result.hasErrors()) {
			gProduct.setFilePath(gachiFarm.getProductImageByPid(gProduct.getProductId()).getImgPath());
			model.addAttribute("groupProduct", gProduct);
			model.addAttribute("user", account);
			return "Group/GroupAttendForm";
		}
		String userId = account.getUserId();
		if (gProduct.getUserId().equals(userId) && gProduct.getStatus().equals("오픈 전")) {
			gProduct.setStatus("진행 중");
		}
		int newQty = gProduct.getCurrQty() + gBuyer.getQty();
		if (newQty > gProduct.getLimitQty()) {
			resultAtt = false;
			model.addAttribute("qty", gProduct.getLimitQty() - gProduct.getCurrQty());
			model.addAttribute("resultAtt", resultAtt);
			return "Group/ResultGroupAttend";
		}
		else if (newQty == gProduct.getLimitQty()) {
			gProduct.setStatus("마감");
			gachiFarm.updateCompleteGroup(new Date(), gProductId);
			gBuyer.setOrderDate(new Date());
		}

		gBuyer.setUserName(account.getUserName());
		gBuyer.setUserId(account.getUserId());
		gBuyer.setPhone(account.getPhone());
		gBuyer.setGroupProductId(gProduct.getgProductId());
		gProduct.setCurrQty(gProduct.getCurrQty() + gBuyer.getQty());
		gachiFarm.updateGroupProduct(gProduct);
		gachiFarm.insertGroupBuyer(gBuyer);
		
		model.addAttribute("groupBuyer", gBuyer);
		model.addAttribute("resultAtt", resultAtt);
		return "Group/ResultGroupAttend";
	}
}
