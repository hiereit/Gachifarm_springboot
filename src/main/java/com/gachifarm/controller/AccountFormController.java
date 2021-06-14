package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.gachifarm.service.GachiFarmFacade;

@Controller
@RequestMapping({"/user/newAccount.do","/user/editAccount.do"})
public class AccountFormController {

	@Value("EditAccountForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setPetStore(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	/*
	 * @Autowired private AccountFormValidator validator; public void
	 * setValidator(AccountFormValidator validator) { this.validator = validator; }
	 */
	
	@ModelAttribute("accountForm")
	public AccountForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			return new AccountForm(
					gachiFarm.getAccount(userSession.getAccount().getUserId()));
		}
		else {	// create a new account
			return new AccountForm();
		}
	}

//	@ModelAttribute("categories")
//	public List<Category> getCategoryList() {
//		return petStore.getCategoryList();
//	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}	
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult result) throws Exception {

		accountForm.getAccount().setUserId(
			accountForm.getAccount().getUserId());

//		if (request.getParameter("account.profile.listOption") == null) {
//			accountForm.getAccount().setListOption(false);
//		}
		
		
//		validator.validate(accountForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
			if (accountForm.isNewAccount()) {
				gachiFarm.insertAccount(accountForm.getAccount());
			}
			else {
				gachiFarm.updateAccount(accountForm.getAccount());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.username", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		UserSession userSession = new UserSession(
				gachiFarm.getAccount(accountForm.getAccount().getUserId()));
//		PagedListHolder<Product> myList = new PagedListHolder<Product>(
//				gachiFarm.getProductListByCategory(
//					accountForm.getAccount().getProfile().getFavouriteCategoryId()));
//		myList.setPageSize(4);
//		userSession.setMyList(myList);
		session.setAttribute("userSession", userSession);
		return successViewName;
		
	}
}
