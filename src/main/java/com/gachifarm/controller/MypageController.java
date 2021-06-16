package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;
@Controller
@SessionAttributes("login")
public class MypageController {
	
	UserSession userSession;
	
	GachiFarmFacade gachiFarm;
	
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
//	@ModelAttribute(name="login")
//	public SignupCommand formBacking() {
//		return new SignupCommand();
//	}
	
	@GetMapping("/user/mypage")
	public String mypage(@ModelAttribute("login") Account account, Model model, HttpServletRequest request) {
//		Account account = userSession.getAccount();
		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");


		
		System.out.println(userSession.getAccount());
		return "Account/MypageLayout";
	
	}
	
	@PostMapping("/user/mypage/update")
	public String updateAccount(@ModelAttribute("login") SignupCommand changeAccount, BindingResult result, Model model) {
		
//		String password = userSession.getAccount().getPassword();
//		System.out.println("!!!!!!!!!!!!!!!!!!!!!!user: " + userSession.getAccount());
		
		return "Account/MypageLayout";
	}
}
