package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GroupProductFacade;

@Controller
public class GroupProductFormController {
	
	@Autowired
	private GroupProductFacade gProductFacade;
	public void setGroupProductFacade(GroupProductFacade gProductFacade) {
		this.gProductFacade = gProductFacade;
	}
	
	@Autowired
	ProductDao productDao;

	@RequestMapping("/group/product/registerForm")
	public String newGroupProductForm(HttpServletRequest request, Model model) {
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		/*
		if (userSession == null) {
			return "로그인페이지";
		}*/
		int productId = 1; // 매개변수로 받아야됨
		// 상품 정보 가져오는 거 Facade에 추가
		
		Product product = productDao.getProduct(productId);
		model.addAttribute("product", product);
		model.addAttribute("groupProduct", new GroupProduct());
		return "Group/GroupProductForm";
	}
	
	@RequestMapping("/group/product/register")
	public String submit(@ModelAttribute("product") Product product, @ModelAttribute("groupProduct") GroupProduct groupProduct) {
		// usersession 이용
		System.out.println("!!!!" + groupProduct + "!!!!");
		String loginId = "DONGDUK01";
		groupProduct.setUserId(loginId);
		gProductFacade.insertGroupProduct(groupProduct, product);
		return "Group/GroupProductForm";
	}
}
