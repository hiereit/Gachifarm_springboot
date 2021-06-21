package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachifarm.domain.Review;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ViewReviewController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@RequestMapping("/review/{reviewId}")
	public String viewGroupProduct(
			@PathVariable("reviewId") int reviewId, Model model) throws Exception {
		Review review = gachiFarm.getReviewById(reviewId);
		review.setPrdtFilePath(gachiFarm.getImgPath(review.getProductId()));
		model.addAttribute("review", review);
		model.addAttribute("product", gachiFarm.getProduct(review.getProductId()));
		model.addAttribute("reviewImg", gachiFarm.getReviewImageById(reviewId));
		System.out.println(gachiFarm.getReviewImageById(reviewId).getImgPath());
		return "Review/ReviewDetail";
	}
}
