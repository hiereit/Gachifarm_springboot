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
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes({"userSession", "myorders"})
public class MypageMyOpenGroupsController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping("/user/mypage/mygroup/open")
	public String myopengroups(HttpSession session, Model model, HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");

		Account account = userSession.getAccount();
		List<GroupProduct> gpList = gachiFarm.findGroupProductByUserId(account.getUserId());
		if(gpList != null) {
			model.addAttribute("groupProducts", gpList);
		}

		return "Account/MypageMyOpenGroup";
	}
	
}
