package com.gachifarm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes(value="product")
public class GroupProductFormController {
	
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@Autowired
	ProductDao productDao;

	@RequestMapping("/group/product/registerForm")
	public String newGroupProductForm(HttpServletRequest request, Model model, HttpSession session) {
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
		session.setAttribute("product", product);
		return "Group/GroupProductForm";
	}
	
	@RequestMapping("/group/product/register")
	public String submit(@ModelAttribute("groupProduct") GroupProduct groupProduct, HttpSession session) {
		// usersession 이용
		Product product = (Product) session.getAttribute("product");
		System.out.println("!!!!" + product + "!!!!");
		String loginId = "DONGDUK01";
		groupProduct.setUserId(loginId);
		groupProduct.setProductId(product.getProductId());
		System.out.println("!!!!" + groupProduct.getProductId() + "!!!!");
		gachiFarm.insertGroupProduct(groupProduct, product);
		return "Group/GroupProductForm";
	}
}
