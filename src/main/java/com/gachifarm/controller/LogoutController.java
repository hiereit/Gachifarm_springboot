package com.gachifarm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class LogoutController {
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("로그아웃!");
		session.removeAttribute("account");
		session.invalidate();
		return "redirect:/";
	}
}
