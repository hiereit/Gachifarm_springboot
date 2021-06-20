package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("account")
public class ViewMainController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}

	@RequestMapping("main")
	public ModelAndView viewCart(HttpSession userSession) throws Exception {
		//String userId = ((Account) userSession.getAttribute("account")).getUserId();
		ModelAndView mav = new ModelAndView("Main");
		String randomImagePath = gachifarm.getRandomImagePath();
		
		List<Integer> bestProductIds = gachifarm.getBestProductIds();
		List<Product> bestProducts = new ArrayList<Product>();
		HashMap<Integer, String> bestImgPaths = new HashMap<>();
		for (Integer id : bestProductIds) {
			Product product = gachifarm.getProduct(id);
			ProductImage productImage = gachifarm.getProductImageByPid(id);
			if(productImage == null) {
				bestImgPaths.put(id, "/images/noImage.png");
			}
			else {
				bestImgPaths.put(id, productImage.getImgPath());
			}
			bestProducts.add(product);
		}
		
		List<Integer> newProductIds = gachifarm.getNewProductIds();
		List<Product> newProducts = new ArrayList<Product>();
		HashMap<Integer, String> newImgPaths = new HashMap<>();
		for (Integer id : newProductIds) {
			Product product = gachifarm.getProduct(id);
			ProductImage productImage = gachifarm.getProductImageByPid(id);
			if(productImage == null) {
				newImgPaths.put(id, "/images/noImage.png");
			}
			else {
				newImgPaths.put(id, productImage.getImgPath());
			}
			newProducts.add(product);
		}
		mav.addObject("random", randomImagePath);
		mav.addObject("bestImages", bestImgPaths);
		mav.addObject("bestProducts", bestProducts);
		mav.addObject("nImages", newImgPaths);
		mav.addObject("nProducts", newProducts);
		return mav;
	}
}
