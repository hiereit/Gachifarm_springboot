package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
@RequestMapping("/login")
public class LoginController {
	GachiFarmFacade gachiFarm;
	
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	
	@GetMapping
	public String form() {
		return "Account/LoginForm";
	}
	
	@ModelAttribute
	public LoginCommand formBacking() {
		return new LoginCommand();
	}
	
	@PostMapping
	public String submit(@ModelAttribute("LoginForm") LoginCommand loginCommand, BindingResult result, Model model) {
		// new LoginCommandValidator().validate(loginCommand, result);
//		if (result.hasErrors()) {
//			return "Account/LoginForm";
//		}
//		try {
//			authenticator.authenticate(loginCommand);
//			return "redirect:/index";
//		} catch (AuthenticationException e) {
//			result.reject("invalidIdOrPassword", new Object[] { loginCommand
//					.getUserId() }, null);
//			return formViewName;
//		}
		

		model.addAttribute("account", new Account());
		System.out.println("userId: " + loginCommand.getUserId());
		System.out.println("password: " + loginCommand.getUserId());
		return "Account/ConfirmSignup";
	}


}
