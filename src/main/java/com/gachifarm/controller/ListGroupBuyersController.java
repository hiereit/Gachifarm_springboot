package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.repository.GroupBuyersRepository;
import com.gachifarm.repository.GroupProductRepository;
import com.gachifarm.service.GroupProductFacade;

@Controller
public class ListGroupBuyersController {
	private GroupProductFacade gProductFacade;
	
	@Autowired(required=false)
	public void setGroupProductFacade(GroupProductFacade gProductFacade) {
		this.gProductFacade = gProductFacade;
	}

	@RequestMapping("/group/listBuyers")
	public ModelAndView handleRequest(
			@RequestParam("groupProductId") int groupProductId) throws Exception {
		return new ModelAndView("/Group/GroupBuyersList", "groupBuyerList", 
				gProductFacade.getGroupBuyersByGroupProductId(groupProductId));
	}
}
