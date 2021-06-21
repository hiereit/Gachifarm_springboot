package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ViewGroupProductController {
	@Autowired
	private GachiFarmFacade gachiFarm;

	@RequestMapping("/group/product/{gProductId}")
	public ModelAndView viewGroupProduct(
			@PathVariable("gProductId") int gProductId) throws Exception {
		GroupProduct gProduct = gachiFarm.getGroupProduct(gProductId);
		gProduct.setFilePath(gachiFarm.getImgPath(gProduct.getProductId()));
		return new ModelAndView("Group/GroupProductDetail", "gProduct", gProduct);
	}
}
