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

import com.gachifarm.domain.Review;
import com.gachifarm.service.GachiFarmFacade;
@Controller
public class ListReviewController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("product/{productId}/review/list/{pageNo}")
    public String productReviewList(@PageableDefault Pageable pageable, @PathVariable("productId") int productId,
    		@PathVariable("pageNo") int pageNo, Model model){
		int count = 10;
		Page<Review> reviewPage = gachiFarm.getReviewListbyPageAndProductId(pageable, pageNo, count, productId);
		List<Review> reviewList = reviewPage.getContent();
		model.addAttribute("productId", productId);
		model.addAttribute("reviewPage", reviewPage);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("count", count);
		return "Review/ReviewList";
    }
}
