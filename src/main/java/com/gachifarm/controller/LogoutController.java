package com.gachifarm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

public class LogoutController {
	@RequestMapping("/user/logout")
	public String handleRequest(HttpSession session) throws Exception {
		System.out.println("로그아웃!");
		session.removeAttribute("userSession");
		session.invalidate();
		return "Main";
	}
}
