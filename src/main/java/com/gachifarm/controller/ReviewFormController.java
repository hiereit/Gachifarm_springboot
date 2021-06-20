package com.gachifarm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.gachifarm.domain.Review;
import com.gachifarm.domain.ReviewImage;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ReviewFormController {
	@Autowired
	private GachiFarmFacade gachiFarm;	
	
	@RequestMapping(value="/review/registerForm/{productId}/{lineProductId}")
	public String newReviewForm(
			@PathVariable("productId") int productId, @PathVariable("lineProductId") int lineProductId,
			Model model) {
		Review review = new Review();
		review.setProductId(productId);
		review.setLineProductId(lineProductId);
		model.addAttribute("product", gachiFarm.getProduct(productId));
		model.addAttribute("review", review);
		return "Review/ReviewForm";
	}
	
	@RequestMapping("/review/register")
	public String submit(@ModelAttribute("review") @Valid Review review, BindingResult result, Model model, HttpServletRequest request, 
			@RequestParam("imgFile") MultipartFile file) throws IOException {
		
		if (result.hasErrors()) {
			model.addAttribute("product", gachiFarm.getProduct(review.getProductId()));
			return "Review/ReviewForm";
		}
		
		final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";
		File convertFile = new File(FILE_PATH, file.getOriginalFilename());
		file.transferTo(convertFile);
		
		String imgPath = "/images/" + file.getOriginalFilename();
		System.out.println(imgPath);
		ReviewImage reviewImg = new ReviewImage();
		reviewImg.setImgPath(imgPath);
		reviewImg.setImgName(file.getOriginalFilename());
		review.setUserId("DONGDUK02");
		review.setOrderId(gachiFarm.findByLineProductId(review.getLineProductId()).getOrderId());
		gachiFarm.saveReview(review);
		reviewImg.setReviewId(review.getReviewId());
		gachiFarm.saveReviewImage(reviewImg);
		return "redirect:/review/"+review.getReviewId();
	}
}
