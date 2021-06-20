package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.GroupProduct;
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

	@RequestMapping("/group/product/{gProductId}")
	public ModelAndView viewGroupProduct(
			@PathVariable("gProductId") int gProductId) throws Exception {
		GroupProduct gProduct = gachiFarm.getGroupProduct(gProductId);
		gProduct.setFilePath(gachiFarm.getProductImageByPid(gProduct.getProductId()).getImgPath());
		return new ModelAndView("Group/GroupProductDetail", "gProduct", gProduct);
	}
}
