package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ViewGroupProductController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@Autowired
	ProductDao productDao;

	@RequestMapping("/group/product/{gProduct_id}")
	public String viewGroupProduct(
			@PathVariable("gProduct_id") int gProductId,
			Model model) throws Exception {
		GroupProduct gProduct = gachiFarm.getGroupProduct(gProductId);
		model.addAttribute("gProduct", gProduct);
		Product product = productDao.getProduct(gProduct.getProductId());
		model.addAttribute("product", product);
		return "Group/GroupProductDetail";
	}
}
