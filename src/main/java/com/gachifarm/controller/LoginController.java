package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;
@Controller
@SessionAttributes("userSession")
public class LoginController {
	GachiFarmFacade gachiFarm;
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("loginForm")
	public String form(Model model) {
		model.addAttribute("loginCommand", new LoginCommand());
		return "Account/LoginForm";
	}

	@RequestMapping("login")
	public String handleRequest(@ModelAttribute("loginCommand") LoginCommand loginCommand, Model model,
			@RequestParam(value="loginForwardAction", required=false) String forwardAction) throws Exception {
		System.out.println("loginController에 왔니????????????????????????????????????");
		Account account = this.gachiFarm.findAccount(loginCommand.getUserId(), loginCommand.getPassword());
		if(account != null) {
			System.out.println("Account null 아니지???????????????????????????????????????????");
			UserSession userSession = new UserSession(account);
			model.addAttribute("userSession", userSession);
			if (forwardAction != null) { 
				System.out.println("forwardAction null 아니니?????????????????????????????????????");
				System.out.println("forwardAction은 " + forwardAction + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				return "redirect:" + forwardAction;
			}
			else {
				System.out.println("forwardAction null이래!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				return "redirect:/main";
			}
		}
		String str = "아이디 또는 비밀번호가 일치하지 않습니다";
		model.addAttribute("str", str);
		System.out.println("로그인 실패!");
		return "Account/LoginForm";
	}
}