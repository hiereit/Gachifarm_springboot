package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.domain.Store;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class StoreRegController {

	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarmFacade(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	
	@RequestMapping("store/registerForm")
	public String showStoreForm(Model model) {
		model.addAttribute("storeCommand", new StoreRegRequest());
		
		return "StoreForm";
	}
	
	@GetMapping("store/regist")
	public String returnStoreForm() {
		return "redirect:/store/registerForm";
	}
	
	@PostMapping("store/regist")
	public ModelAndView handleStoreForm(@ModelAttribute("storeCommand") StoreRegRequest regReq) {
		Store store 
		   = new Store("test1", regReq.getStoreName(), regReq.getStoreInfo());
				
		gachifarm.insertStore(store);
		return new ModelAndView("test");
	}
}
