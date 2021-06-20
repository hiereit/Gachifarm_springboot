package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@RequestMapping("/user/deleted")
@SessionAttributes("userSession")
public class DeleteAccountController {
	GachiFarmFacade gachiFarm;
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping
	public String delete(HttpSession session, HttpServletRequest request) {
		System.out.println("delete함수 여기로 오긴 했나?");
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
//		Account account = (Account) session.getAttribute("account");
		Account account = userSession.getAccount();
		System.out.println("회원탈퇴!");
		session.removeAttribute("account");
		session.invalidate();
		gachiFarm.deleteByUserId(account.getUserId());
		return "Account/ConfirmDeleteAccount";
	}

}
