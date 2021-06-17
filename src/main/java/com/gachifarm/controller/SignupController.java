package com.gachifarm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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


	@PostMapping(value="/user/confirm")
	public String submit(@Valid @ModelAttribute("SignupForm") SignupCommand newAccount, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "Account/SignupForm";
		}
		
		String userId = newAccount.getUserId();
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
		
		model.addAttribute("account", account);
		gachiFarm.save(account);
		
		return "Account/ConfirmSignup";
	}
	

}
