package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@RequestMapping("/user/mypage")
@SessionAttributes({"account", "myorders"})
public class MypageController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@ModelAttribute(name="signupCommand")
	public SignupCommand formBacking() {
		return new SignupCommand();
	}

	@GetMapping
	public String mypage(HttpSession session, Model model, HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		Account sessionAccount = userSession.getAccount();
		
//		Account sessionAccount = (Account) session.getAttribute("account");
		SignupCommand signupCommand = new SignupCommand(
				sessionAccount.getUserId(), sessionAccount.getPassword(), 
				sessionAccount.getUserName(), sessionAccount.getPhone(),
				sessionAccount.getEmail(), sessionAccount.getZip(),
				sessionAccount.getAddr1(), sessionAccount.getAddr2()
				);
		
		model.addAttribute("account", sessionAccount);
		model.addAttribute("signupCommand", signupCommand);
		
		System.out.println("MypageController - " + sessionAccount.getUserId());

		return "Account/MypageUpdateSignupForm";
	}

	
	@PostMapping
	public String updateAccount(@Valid @ModelAttribute("signupCommand") SignupCommand signupCommand, BindingResult result, HttpSession session, Model model) {
		System.out.println("==========================" + signupCommand.getPhone());

		String password = signupCommand.getPassword();
		System.out.println("updateAccount() - password: " + password);

		if(result.hasErrors()) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(result.toString());
			if(!signupCommand.isPasswordEqualToConfirmPassword()) {
				System.out.println("!불일치!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				String str = "비밀번호 불일치";
				model.addAttribute("str", str);

				return "Account/MypageUpdateSignupForm";
			}

			return "Account/MypageUpdateSignupForm";
		}
		
		String phone = signupCommand.getPhone();
		System.out.println("updateAccount() - phone: " + phone);
		String email = signupCommand.getEmail();
		String zip = signupCommand.getZip();
		String addr1 = signupCommand.getAddr1();
		String addr2 = signupCommand.getAddr2();

		System.out.println("updateAccount() - " + session.getAttribute("account").toString());		
		Account account = (Account) session.getAttribute("account");

		account.setUserId(signupCommand.getUserId());
		account.setUserName(signupCommand.getUserName());
		
		account.setPassword(password);
		account.setPhone(phone);
		account.setEmail(email);
		account.setZip(zip);
		account.setAddr1(addr1);
		account.setAddr2(addr2);
		System.out.println("updateAccount: " + account);
		
		model.addAttribute("changeAccount", account);
		gachiFarm.save(account);
		System.out.println("수정완료!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String str = "수정이 완료되었습니다";
		model.addAttribute("str2", str);

		return "Account/MypageUpdateSignupForm";
	}
	
}