package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
public class ViewMainController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	
	@RequestMapping(value="/") public String index() { return "redirect:/main"; }

	@RequestMapping("main")
	public ModelAndView viewCart() throws Exception {
		ModelAndView mav = new ModelAndView("Main");
		String randomImagePath = gachifarm.getRandomImagePath();
		
		List<Integer> bestProductIds = gachifarm.getBestProductIds();
		List<Product> bestProducts = new ArrayList<Product>();
		HashMap<Integer, String> bestImgPaths = new HashMap<>();
		for (Integer id : bestProductIds) {
			Product product = gachifarm.getProduct(id);
			bestImgPaths.put(id, gachifarm.getImgPath(id));
			bestProducts.add(product);
		}
		
		List<Integer> newProductIds = gachifarm.getNewProductIds();
		List<Product> newProducts = new ArrayList<Product>();
		HashMap<Integer, String> newImgPaths = new HashMap<>();
		for (Integer id : newProductIds) {
			Product product = gachifarm.getProduct(id);
			newImgPaths.put(id, gachifarm.getImgPath(id));
			newProducts.add(product);
		}
		mav.addObject("random", randomImagePath);
		mav.addObject("bestImages", bestImgPaths);
		mav.addObject("bestProducts", bestProducts);
		mav.addObject("nImages", newImgPaths);
		mav.addObject("nProducts", newProducts);
		return mav;
	}
	
	@RequestMapping("help")
	public String help() {
		return "Help.html";
	}
}
