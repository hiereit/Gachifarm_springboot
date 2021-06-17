package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

	@RequestMapping("/group/listBuyers")
	public ModelAndView handleRequest(
			@RequestParam("groupProductId") int groupProductId) throws Exception {
		return new ModelAndView("/Group/GroupBuyersList", "groupBuyerList", 
				gachiFarm.getGroupBuyersByGroupProductId(groupProductId));
	}
}
