package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListGroupBuyersController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("/group/buyer/list/{gProductId}")
	public ModelAndView handleRequest(
			@PathVariable("gProductId") int gProductId) throws Exception {
		// id RequestParam으로 변경
		return new ModelAndView("/Group/GroupBuyersList", "groupBuyerList", 
				gachiFarm.getGroupBuyersByGroupProductId(gProductId));
	}
}
