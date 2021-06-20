package com.gachifarm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes({"account", "myorders"})
public class MypageMyOpenGroupsController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping("/user/mypage/mygroup/open")
	public String myopengroups(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		List<GroupProduct> gpList = gachiFarm.findGroupProductByUserId(account.getUserId());
//		System.out.println(gpList);
		if(gpList != null) {
			model.addAttribute("groupProducts", gpList);
		}

		return "Account/MypageMyOpenGroup";
	}
	
}
