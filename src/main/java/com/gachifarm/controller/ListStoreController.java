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

import com.gachifarm.domain.Product;
import com.gachifarm.domain.Store;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListStoreController {
	private GachiFarmFacade gachifarm;
	
	@Autowired
	public void setGachiFarmFacade(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	
	@RequestMapping("store/list/all")
    public String getAllStoreName(Model model){
		//Product products = this.gachifarm.getProduct(1);
		List<Store> stores = this.gachifarm.getAllStore();
        
		//model.addAttribute("data", "product/list");
        model.addAttribute("data", stores);
        model.addAttribute("data2", "HI GACHI STORE LIST");
       
        return "StoreList";
    }
	@RequestMapping("store/{storeName}/{pageNo}")
    public String getStore(@PageableDefault Pageable pageable,
    		@PathVariable("pageNo") int pageNo, @PathVariable String storeName, Model model){
		//Product products = this.gachifarm.getProduct(1);
		//Store stores = this.gachifarm.getStore("DONGDUK01");
		Store store = this.gachifarm.getStoreName(storeName);
		//model.addAttribute("data", "product/list");
        Page<Product> products = gachifarm.getsProductbyUserId(pageable, store.getUserId(), pageNo); 
        List<Product> product = products.getContent();
        //List<Product> product = gachifarm.getAllProductByStore(store.getUserId());
		
        
		model.addAttribute("data", products);
        model.addAttribute("data2", product);
        model.addAttribute("sUserId", store.getUserId());
        model.addAttribute("myUserId", "DONGDUK02");
        model.addAttribute("storename", storeName);
        System.out.println(storeName);
        return "Store";
    }
}
