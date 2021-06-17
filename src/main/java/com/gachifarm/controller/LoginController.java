package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("account")
@RequestMapping("/login")
public class LoginController {

	GachiFarmFacade gachiFarm;
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@ModelAttribute("login")
	public LoginCommand formBacking() {
		return new LoginCommand();
	}

	@GetMapping
	public String form() {
		return "Account/LoginForm";
	}
	
	@PostMapping
	public String login(@ModelAttribute("login") LoginCommand loginCommand, Model model) {
		Account account = this.gachiFarm.findAccount(loginCommand.getUserId(), loginCommand.getPassword());
		if(account != null) {
			UserSession userSession = new UserSession(account);
			System.out.println(userSession.getAccount());
	//		model.addAttribute("account", account);
			model.addAttribute("account", userSession.getAccount());
			System.out.println(account);
			System.out.println("로그인 성공!");
			return "Main";
		}
		String str = "아이디 또는 비밀번호가 일치하지 않습니다";
		model.addAttribute("str", str);
		System.out.println("로그인 실패!");
		return "Account/LoginForm";
	}

}
