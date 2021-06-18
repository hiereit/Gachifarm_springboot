package com.gachifarm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;

@Controller

public class SignupController {

	GachiFarmFacade gachiFarm;
	
	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	
	@GetMapping("/signup")
	public String form() {
		
		return "Account/SignupForm";
	}
	
	@ModelAttribute(name="newAccount")
	public SignupCommand formBacking() {
		return new SignupCommand();
	}


	@PostMapping("/signup")
	public String submit(@Validated @ModelAttribute("newAccount") SignupCommand newAccount, BindingResult result, Model model, HttpSession session) {
		if(result.hasErrors()) {
			System.out.println("SignupController-submit() 에러남!!!");
			if(!newAccount.isPasswordEqualToConfirmPassword()) {
				String str = "비밀번호 불일치";
				model.addAttribute("str", str);
			}
			String userId = newAccount.getUserId();
			if(gachiFarm.findByUserId(userId) != null) {
				String userIdExistedStr = "이미 존재하는 아이디입니다."; 
				model.addAttribute("userIdExistedStr", userIdExistedStr);
				return "Account/SignupForm";
			}
			return "Account/SignupForm";
		}
		
		
		String userId = newAccount.getUserId();
		if(gachiFarm.findByUserId(userId) != null) {
			String userIdExistedStr = "이미 존재하는 아이디입니다."; 
			model.addAttribute("userIdExistedStr", userIdExistedStr);
			return "Account/SignupForm";
		}
		
		String password = newAccount.getPassword();
		String userName = newAccount.getUserName();
		String phone = newAccount.getPhone();
		String email = newAccount.getEmail();
//		Address address = newAccount.getAddress();
		String zip = newAccount.getZip();
		String addr1 = newAccount.getAddr1();
		String addr2 = newAccount.getAddr2();
		
		Account account = new Account();
		account.setUserId(userId);
		account.setPassword(password);
		account.setUserName(userName);
		account.setPhone(phone);
		account.setEmail(email);
//		account.setAddress(address);
		account.setZip(zip);
		account.setAddr1(addr1);
		account.setAddr2(addr2);
		
		System.out.println("SignupController-submit()");
		System.out.println("userId: " + newAccount.getUserId());
		System.out.println("password: " + newAccount.getPassword());
		System.out.println("userName: " + newAccount.getUserName());
		System.out.println("email: " + newAccount.getEmail());
		
		System.out.println("===");
		
		System.out.println("userId: " + account.getUserId());
		System.out.println("password: " + account.getPassword());
		System.out.println("userName: " + account.getUserName());
		System.out.println("email: " + account.getEmail());
		System.out.println("=====================================================");
	
		UserSession userSession = new UserSession(gachiFarm.findAccount(userId, password));
		session.setAttribute("userSession", userSession);
		model.addAttribute("account", account);
		gachiFarm.save(account);
		
		return "Account/ConfirmSignup";
	}

}
