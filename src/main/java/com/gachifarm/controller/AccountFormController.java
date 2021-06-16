package com.gachifarm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Address;
import com.gachifarm.repository.AccountRepository;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class AccountFormController {

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
	public String submit(@ModelAttribute("SignupForm") SignupCommand newAccount, BindingResult result, Model model) {

		String userId = newAccount.getUserId();
		String password = newAccount.getPassword();
		String userName = newAccount.getUserName();
		String phone = newAccount.getPhone();
		String email = newAccount.getEmail();
		Address address = newAccount.getAddress();
		
		Account account = new Account();
		account.setUserId(userId);
		account.setPassword(password);
		account.setUserName(userName);
		account.setPhone(phone);
		account.setEmail(email);
		account.setAddress(address);
		
		
		System.out.println("userId: " + newAccount.getUserId());
		System.out.println("password: " + newAccount.getPassword());
		System.out.println("userName: " + newAccount.getUserName());
		System.out.println("email: " + newAccount.getEmail());
		
		System.out.println("=====================================================");
		
		System.out.println("userId: " + account.getUserId());
		System.out.println("password: " + account.getPassword());
		System.out.println("userName: " + account.getUserName());
		System.out.println("email: " + account.getEmail());
		
		model.addAttribute("account", account);
		gachiFarm.save(account);
		
		return "Account/ConfirmSignup";
	}
	

}
