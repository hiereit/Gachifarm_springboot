package com.gachifarm.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.Store;
import com.gachifarm.repository.LineProductRepository;
import com.gachifarm.repository.OrdersRepository;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListStoreController {
	private GachiFarmFacade gachifarm;
	
	@Autowired
	public void setGachiFarmFacade(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	@Autowired
	private LineProductRepository lineProductRepository;
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	@RequestMapping("user/store")
    public String getMyStore(Model model, HttpSession userSession) throws UnsupportedEncodingException{
		//Product products = this.gachifarm.getProduct(1);
		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();
		if(userSession.getAttribute("userSession") == null)
			return "Account/LoginForm";
		else {
				System.out.println(userId);
				Store store = this.gachifarm.getStore(userId);
				if(store == null) {
					System.out.println("==여기서 걸렸다.==========");
					return "redirect:/store/registerForm";
				}
				else {
					System.out.println(store.getStoreName()+"============================");
					String encodedParam = URLEncoder.encode(store.getStoreName(), "UTF-8");
					return "redirect:/store/" + encodedParam + "/1";
				}
		}
    }
	
	@RequestMapping("store/list")
    public String getAllStoreName(Model model, HttpSession userSession){
		//Product products = this.gachifarm.getProduct(1);
		List<Store> stores = this.gachifarm.getAllStore();
        
		//model.addAttribute("data", "product/list");
        model.addAttribute("data", stores);
        model.addAttribute("data2", "HI GACHI STORE LIST");
        
       
        return "Store/StoreList";
    }
	@RequestMapping("store/{storeName}/{pageNo}")
    public String getStore(@PageableDefault Pageable pageable, HttpSession userSession,
    		@PathVariable("pageNo") int pageNo, @PathVariable("storeName") String storeName, Model model){
		//Product products = this.gachifarm.getProduct(1);
		//Store stores = this.gachifarm.getStore("DONGDUK01");
		//세션에 저장된 account 가져오기
		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();
		if(userSession.getAttribute("userSession") == null)
			return "Account/LoginForm";
		else {
				System.out.println(userId);
				Store store = this.gachifarm.getStore(userId);
				if(store == null) {
					System.out.println("==여기서 걸렸다.==========");
					//return "redirect:/store/registerForm";
				}
		}
		
		//storeName으로 해당 스토어 객체 가져오기
		Store store = this.gachifarm.getStoreName(storeName);
		//model.addAttribute("data", "product/list");
		//스토어 출력
        Page<Product> products = gachifarm.getsProductbyUserId(pageable, store.getUserId(), pageNo); 
        List<Product> product = products.getContent();
        //List<Product> product = gachifarm.getAllProductByStore(store.getUserId());
		
        HashMap<Integer, String> map = new HashMap<>();
        
        for(int i = 0; i<product.size(); i++) {
        	int pid = product.get(i).getProductId();
        	if(gachifarm.getProductImageByPid(pid) ==null) {
        		map.put(pid, "https://hgo.kr/shop/img/no_image.gif");
        	}
        	else {
        		map.put(pid, gachifarm.getProductImageByPid(pid).getImgPath());
        	}
        }
       
        model.addAttribute("map", map);
		model.addAttribute("data", products);
        model.addAttribute("data2", product);
        model.addAttribute("sInfo", store.getStoreInfo());
        model.addAttribute("sUserId", store.getUserId());  			//store의 주인장이 누구냐
        model.addAttribute("myUserId", userId); //store의 방문자가 누구냐
        model.addAttribute("storename", storeName);
        System.out.println(storeName);
        return "Store/Store";
    }
	@RequestMapping("/store/order/product/list/{storeName}")
	public String getStoreOrderList( 
			@PathVariable("storeName") String storeName, HttpSession session, Model model)
	{
		//session에 저장된 accont 정보 가져오기
		Account sessionAccount = (Account) session.getAttribute("account");
		
		Store store = gachifarm.getStore(sessionAccount.getUserId());
		
		//List<Product> products = gachifarm.getStoreOrderProduct(sessionAccount.getUserId());
		
		
		//List<Orders> storeOrders = 
		model.addAttribute("store", store);
		//model.addAttribute("products", products);
		return "Store/StoreOrderList";
	}
	@RequestMapping("/store/product/order/status/{pageNo}")
	public String getStoreOrderList( @PageableDefault Pageable pageable,
			@PathVariable("pageNo")int pageNo, Model model, HttpSession session) {
		Account sessionAccount = (Account) session.getAttribute("account");
		
		//스토어정보
		Store store = gachifarm.getStore(sessionAccount.getUserId());
		
		//스토어 출력
        Page<Product> products = gachifarm.getsProductbyUserId(pageable, store.getUserId(), pageNo); 
        List<Product> product = products.getContent();
        //List<Product> product = gachifarm.getAllProductByStore(store.getUserId());
		
        HashMap<Integer, String> map = new HashMap<>();
        HashMap<Integer, Integer> map2 = new HashMap<>();
        
        List<LineProduct> lineProducts = lineProductRepository.findAll();
		/* lineProducts.get(0).get */
        HashMap<Integer, Integer> map3 = new HashMap<>();
        
        System.out.println(lineProducts);
        for(LineProduct obj: lineProducts) {
        	if(!map3.containsKey(obj.getProductId())) {
        		map3.put(obj.getProductId(), obj.getQuantity());
        	}
        	else {
        		int total = map3.get(obj.getProductId());
        		int result = total + obj.getQuantity();
        		map3.put(obj.getProductId(), result);
        	}
        }
        
        
        
        List<Orders> orders;
        int cnt;
        for(int i = 0; i<product.size(); i++) {
        	cnt = 0;
        	int pid = product.get(i).getProductId();
        	orders = gachifarm.getStoreOrderProduct(pid);
        	for(Orders j : orders) {
        		cnt++;
        	}
        	map2.put(pid, cnt);
        	if(gachifarm.getProductImageByPid(pid) ==null) {
        		map.put(pid, "https://hgo.kr/shop/img/no_image.gif");
        	}
        	else {
        		map.put(pid, gachifarm.getProductImageByPid(pid).getImgPath());
        	}
        }
       
        model.addAttribute("map", map);
        model.addAttribute("map2", map2);
        model.addAttribute("map3", map3);
		model.addAttribute("data", products);
        model.addAttribute("data2", product);
        model.addAttribute("storename", store.getStoreName());
        model.addAttribute("sInfo", store.getStoreInfo());
        model.addAttribute("sUserId", store.getUserId());  			//store의 주인장이 누구냐
        model.addAttribute("myUserId", sessionAccount.getUserId()); //store의 방문자가 누구냐
        
	
		//model.addAttribute("orders", orders);
		model.addAttribute("store", store);

		
		
		return "Store/StoreSaleStatus";
	}
	
	
	
	
	
	
	
	
}
