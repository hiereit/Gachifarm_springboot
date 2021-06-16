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
@SessionAttributes("login")
@RequestMapping("/login")
public class LoginController {
	
	GachiFarmFacade gachiFarm;
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
@	ModelAttribute("login")
	public LoginCommand formBacking() {
		return new LoginCommand();
	}
	
	@GetMapping
	public String form() {
		return "Account/LoginForm";
	}
/*	
 	private Authenticator authenticator;
	@PostMapping
	public String submit(@ModelAttribute("LoginForm") LoginCommand loginCommand,
							BindingResult result, Model model) {
		model.addAttribute("account", loginCommand);
		System.out.println("LoginController-loginCommand.userId: " + loginCommand.getUserId());
		
		if(result.hasErrors()) {
			return "Account/LoginForm";
		}
		try {
			authenticator.authenticate(loginCommand.getUserId(), loginCommand.getPassword());
			return "Account/ConfirmSignup";
		} catch(AuthenticationException ex){
			result.reject("invalidIdOrPassword", new Object[] { loginCommand.getUserId() }, null);
			return "Account/LoginForm";
		}
	
	}
	
	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}
*/
	
	
/*	
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
*/
	
	@PostMapping
	public String login(String userId, String password, Model model) {
		Account account = this.gachiFarm.findAccount(userId, password);
		if(account != null) {
			UserSession userSession = new UserSession(account);
			System.out.println(userSession.getAccount());
			model.addAttribute("account", account);
			System.out.println("로그인 성공!");
			return "Main";
		}
		System.out.println("로그인 실패!");
		return "Account/LoginForm";
	}
	

}
