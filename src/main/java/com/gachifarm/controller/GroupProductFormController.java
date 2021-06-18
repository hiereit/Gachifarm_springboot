package com.gachifarm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class GroupProductFormController {
	
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@Autowired
	ProductDao productDao;

	@RequestMapping("/group/product/registerForm/{productId}")
	public ModelAndView newGroupProductForm(
			@PathVariable("productId") int productId) {
		//UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		/*
		if (userSession == null) {
			return "로그인페이지";
		}*/
		// 상품 정보 가져오는 거 Facade에 추가
		return new ModelAndView("Group/GroupProductForm", "groupProduct", productDao.getProduct(productId));
	}
	
	@RequestMapping("/group/product/register")
	public String submit(@ModelAttribute("groupProduct") GroupProduct groupProduct, HttpSession session) {
		// user session 이용
		Product product = productDao.getProduct(groupProduct.getProduct().getProductId());
		int prdtQty = product.getQuantity();
		int minQty = (int) (prdtQty * 0.05);
		int limitQty = (int) (prdtQty * 0.15);
		product.setQuatity(prdtQty - limitQty);
		String loginId = "DONGDUK01";
		groupProduct.setUserId(loginId);
		groupProduct.setProductId(product.getProductId());
		groupProduct.setMinQty(minQty);
		groupProduct.setLimitQty(limitQty);
		String[] recvPlace = groupProduct.getRecvPlace().split(" ");
		String location = recvPlace[0] + " " + recvPlace[1];
		groupProduct.setLocation(location);
		gachiFarm.insertGroupProduct(groupProduct, product);
		return "redirect:/group/product/list/1";
	}
	
	@RequestMapping("/group/product/updateForm/{gProductId}")
	public ModelAndView updateGroupProductForm(
			@PathVariable("gProductId") int gProductId, Model model) {
		//UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		/*
		if (userSession == null) {
			return "로그인페이지";
		}*/
		// 상품 정보 가져오는 거 Facade에 추가
		return new ModelAndView("Group/GroupProductUpdateForm", "groupProduct", gachiFarm.getGroupProduct(gProductId));
	}
	
	@RequestMapping("/group/product/update")
	public String update(@RequestParam("gProductId") int gProductId, @RequestParam("recvDate") String recvDateStr) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date recvDate = format.parse(recvDateStr);
		GroupProduct groupProduct = gachiFarm.getGroupProduct(gProductId);
		groupProduct.setRecvDate(recvDate);
		gachiFarm.updateGroupProduct(groupProduct);
		return "redirect:/group/product/" + gProductId;
	}
}
