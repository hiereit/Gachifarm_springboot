package com.gachifarm.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class GroupProductFormController {
	
	@Autowired
	private GachiFarmFacade gachiFarm;
	
	@RequestMapping("/group/product/registerForm/{productId}")
	public ModelAndView newGroupProductForm(
			@PathVariable("productId") int productId) {
		GroupProduct groupProduct = new GroupProduct();
		groupProduct.setProduct(gachiFarm.getProduct(productId));
		groupProduct.setProductId(productId);
		groupProduct.setFilePath(gachiFarm.getImgPath(productId));
		return new ModelAndView("Group/GroupProductForm", "groupProduct", groupProduct);
	}
	
	@RequestMapping("/group/product/register")
	public String submit(@ModelAttribute("groupProduct") @Valid GroupProduct groupProduct, BindingResult result, HttpSession session) {
		if (result.hasErrors()) {
			groupProduct.setProduct(gachiFarm.getProduct(groupProduct.getProductId()));
			groupProduct.setFilePath(gachiFarm.getImgPath(groupProduct.getProductId()));
			return "Group/GroupProductForm";
		}
		Product product = gachiFarm.getProduct(groupProduct.getProductId());
		int prdtQty = product.getQuantity();
		int minQty = (int) (prdtQty * 0.05);
		int limitQty = (int) (prdtQty * 0.15);
		product.setQuantity(prdtQty - limitQty);
		String loginId = ((UserSession) session.getAttribute("userSession")).getAccount().getUserId();
		groupProduct.setUserId(loginId);
		groupProduct.setMinQty(minQty);
		groupProduct.setLimitQty(limitQty);
		String[] recvPlace = groupProduct.getRecvPlace().split(" ");
		String location = recvPlace[0] + " " + recvPlace[1];
		groupProduct.setLocation(location);
		groupProduct.setProduct(product);
		gachiFarm.insertGroupProduct(groupProduct);
		gachiFarm.changeGroupOrderStatus(groupProduct);
		return "redirect:/group/product/" + groupProduct.getgProductId();
	}
	
	@RequestMapping("/group/product/updateForm/{gProductId}")
	public ModelAndView updateGroupProductForm(
			@PathVariable("gProductId") int gProductId, Model model) {
		GroupProduct groupProduct = gachiFarm.getGroupProduct(gProductId);
		groupProduct.setFilePath(gachiFarm.getImgPath(groupProduct.getProductId()));
		return new ModelAndView("Group/GroupProductUpdateForm", "groupProduct", groupProduct);
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
