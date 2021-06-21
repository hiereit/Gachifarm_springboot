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

import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListGroupProductController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("/group/product/list/{pageNo}")
    public String gProductListAll(@PageableDefault Pageable pageable,
    		@PathVariable("pageNo") int pageNo, Model model){
		String status = "진행 중";
		int count = 12;
		Page<GroupProduct> gProductPage = gachiFarm.getGroupProductListbyPage(pageable, pageNo, count, status);
		List<GroupProduct> gProductList = gProductPage.getContent();
		
		for (int i = 0; i < gProductList.size(); i++) {
			gProductList.get(i).setFilePath(gachiFarm.getImgPath(gProductList.get(i).getProductId()));
			System.out.println(gProductList.get(i).getFilePath());
		}
		model.addAttribute("gProductPage", gProductPage);
		model.addAttribute("gProductList", gProductList);
	
		return "Group/GroupProductList";
    }
}
