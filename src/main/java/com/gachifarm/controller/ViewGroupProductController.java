package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ViewGroupProductController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("/group/viewProduct.do")
	public String handleRequest(
			@RequestParam("gProductId") int gProductId,
			ModelMap model) throws Exception {
		GroupProduct gProduct = gachiFarm.getGroupProduct(gProductId);
		model.put("gProduct", gProduct);
		return "GroupProduct";
	}
}
