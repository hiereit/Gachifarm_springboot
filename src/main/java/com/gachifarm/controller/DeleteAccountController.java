package com.gachifarm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@RequestMapping("/deleted")
@SessionAttributes("account")
public class DeleteAccountController {
	GachiFarmFacade gachiFarm;
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping
	public String delete(HttpSession session) {
		System.out.println("delete함수 여기로 오긴 했나?");
		Account sessionAccount = (Account) session.getAttribute("account");
		System.out.println("회원탈퇴!");
		session.removeAttribute("account");
		session.invalidate();
		gachiFarm.deleteByUserId(sessionAccount.getUserId());
		return "Account/ConfirmDeleteAccount";
	}

}
