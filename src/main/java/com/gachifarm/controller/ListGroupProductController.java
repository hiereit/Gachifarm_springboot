package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.service.GroupProductFacade;

@Controller
public class ListGroupProductController {
private GroupProductFacade gProductFacade;
	
	@Autowired(required=false)
	public void setGroupProductFacade(GroupProductFacade gProductFacade) {
		this.gProductFacade = gProductFacade;
	}

	@RequestMapping("/group/listProducts.do")
	public ModelAndView handleRequest() throws Exception {
		return new ModelAndView("ListGroupProducts", "gProductList", 
				gProductFacade.getGroupProductList());
	}
}
