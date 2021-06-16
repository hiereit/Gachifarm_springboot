package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListGroupProductController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("/group/listProducts.do")
	public ModelAndView handleRequest() throws Exception {
		return new ModelAndView("ListGroupProducts", "gProductList", 
				gachiFarm.getGroupProductList());
	}
}
