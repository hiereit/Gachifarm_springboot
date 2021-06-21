package com.gachifarm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListGroupBuyersController {
	@Autowired
	private GachiFarmFacade gachiFarm;

	@RequestMapping("/group/buyer/list/{gProductId}/{pageNo}")
	public String buyerListAll(@PageableDefault Pageable pageable,
			@PathVariable("pageNo") int pageNo, @PathVariable("gProductId") int gProductId, Model model) throws Exception {
		int count = 20;
		Page<GroupBuyer> buyerPage = gachiFarm.getBuyerListbyPage(pageable, pageNo, count, gProductId);
		List<GroupBuyer> buyerList = buyerPage.getContent();
		model.addAttribute("buyerPage", buyerPage);
		model.addAttribute("buyerList", buyerList);
		model.addAttribute("count", count);
		return "Group/GroupBuyersList";
	}
}
