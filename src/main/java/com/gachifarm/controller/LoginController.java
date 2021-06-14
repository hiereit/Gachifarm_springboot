package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.domain.Account;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
public class LoginController {
	private GachiFarmFacade gachiFarm;
	@Autowired
	public void setPetStore(GachiFarmFacade gachifarm) {
		this.gachiFarm = gachifarm;
	}
	
	@RequestMapping("/user/signon")
	public ModelAndView handleRequest(HttpServletRequest request,
			@RequestParam("username") String username,
			@RequestParam("password") String password,
			@RequestParam(value="forwardAction", required=false) String forwardAction,
			Model model) throws Exception {
		Account account = gachiFarm.getAccount(username, password);
		if (account == null) {
			return new ModelAndView("Error", "message", 
					"Invalid username or password.  Signon failed.");
		}
		else {
			UserSession userSession = new UserSession(account);
//			PagedListHolder<Product> myList = new PagedListHolder<Product>(
//					this.gachiFarm.getProductListByCategory(
//							account.getFavouriteCategoryId()));
//			myList.setPageSize(4);
//			userSession.setMyList(myList);
			model.addAttribute("userSession", userSession);
			if (forwardAction != null) 
				return new ModelAndView("redirect:" + forwardAction);
			else 
				return new ModelAndView("index");
		}
	}	
}
