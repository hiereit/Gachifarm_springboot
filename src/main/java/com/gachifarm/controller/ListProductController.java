package com.gachifarm.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListProductController {
	/*
	@RequestMapping("hello/index")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }
	*/
	private GachiFarmFacade gachifarm;
	
	@Autowired
	public void setGachiFarmFacade(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	
	//상품 상세정보조회
	@RequestMapping("product/{productId}")
    public String getProduct(Model model, @PathVariable("productId") int productId){
		//Product products = this.gachifarm.getProduct(1);
		//List<Product> products = this.gachifarm.getAllProduct();
		Product product = gachifarm.getProduct(productId);
		
		String link;
        if(gachifarm.getProductImageByPid(productId) == null) {
        	link = "/images/noImage.png";
        }
        else {
        	link = gachifarm.getProductImageByPid(productId).getImgPath();
        }
        
        //HashMap<String, String> saleTypeMap = new HashMap<>();
        //for(p : pr)
        String pSaleType;
        if(gachifarm.getGroupProduct(productId) != null) {
        	pSaleType= product.getSaleType();
        }
        else {
        	pSaleType = product.getSaleType();
        }
     
		model.addAttribute("link", link);
		model.addAttribute("pSaleType", pSaleType);
		model.addAttribute("product", product);
        model.addAttribute("data2", "HI GACHI PRODUCT" + productId);
        
        return "ProductDetail";
    }
	//아직 안쓰임.!!!!!!!!!!!!!!!!!1
	@RequestMapping("product/list")
    public String productList(Model model){
		//List<Product> products = this.gachifarm.getAllProductByType("GACHI");
		//List<Product> products = this.gachifarm.searchAllProdcutListByCategory("VEGI");
		List<Product> products = this.gachifarm.searchAllProdcutList("고구마");
		//model.addAttribute("data", "product/list");
        model.addAttribute("data", products);
        model.addAttribute("data2", "HI GACHI PRODUCT");
        return "Products";
    }
	//전체상품목록 조회 페이지 처리!!!!!!
	@RequestMapping("product/list/all/{pageNo}")
    public String productListAll(@PageableDefault Pageable pageable,
    		@PathVariable("pageNo") int pageNo, Model model){//Model model){
		
		//Product products = this.gachifarm.getProductByName("감자");
		//List<Product> products = this.gachifarm.searchAllProdcutListByCategory("VEGI");
        //model.addAttribute("data", products);
		//List<Product> products = this.gachifarm.findAll(pageNo);
		
		Page<Product> products = gachifarm.getProductListbyPage(pageable, pageNo);
		List<Product> product = products.getContent();
		//model.addAttribute("data", products);
		HashMap<Integer, String> map = new HashMap<>();
	
		for(int i=0;i < product.size();i++) {
			int p_id = product.get(i).getProductId();
			if(gachifarm.getProductImageByPid(p_id) == null) {
				//map.put(p_id, "http://cdn.011st.com/11dims/resize/600x600/quality/75/11src/ak/3/5/6/0/8/6/537356086_B_V4.jpg");
				map.put(p_id, "/images/noImage.png");
			}
			else {
				map.put(p_id, gachifarm.getProductImageByPid(p_id).getImgPath());
				System.out.println(p_id + "  : 1*** :  "+ gachifarm.getProductImageByPid(p_id).getImgPath());
			}		
			System.out.println(p_id + "  : 2*** :  "+ map.get(p_id));
		}
		
		model.addAttribute("map", map);
		model.addAttribute("data2", product);
		model.addAttribute("data", products);
		model.addAttribute("link", "product/list/all/{pageNo}");
		model.addAttribute("isKeyword", false);
		//model.addAttribute("prdtList", products.size());
	
		return "Products";
    }
	@RequestMapping("search/product/list/{keyword}/{pageNo}")
    public String searchProductListAll(@PageableDefault Pageable pageable,
    		@PathVariable("keyword") String keyword, @PathVariable("pageNo") int pageNo, Model model){//Model model){
		
		if(keyword == null){
			return "redirect:/product/list/all/1";
		}
		Page<Product> products = gachifarm.getProductListbyPrdtName(pageable, keyword, pageNo);
		List<Product> product = products.getContent();
		
		System.out.println(product);
		System.out.println(products);
		//List<Product> product = gachifarm.searchAllProdcutList(keyword);
		

		//model.addAttribute("data", products);
		HashMap<Integer, String> map = new HashMap<>();
	
		for(int i=0;i < product.size();i++) {
			int p_id = product.get(i).getProductId();
			if(gachifarm.getProductImageByPid(p_id) == null) {
				//map.put(p_id, "http://cdn.011st.com/11dims/resize/600x600/quality/75/11src/ak/3/5/6/0/8/6/537356086_B_V4.jpg");
				map.put(p_id, "/images/noImage.png");
			}
			else {
				map.put(p_id, gachifarm.getProductImageByPid(p_id).getImgPath());
				System.out.println(p_id + "  : 1*** :  "+ gachifarm.getProductImageByPid(p_id).getImgPath());
			}		
			System.out.println(p_id + "  : 2*** :  "+ map.get(p_id));
		}
		
		model.addAttribute("searchKeyword", keyword);
		System.out.println("searchKeyword:**************"+ keyword);
		model.addAttribute("map", map);
		model.addAttribute("data2", product);
		model.addAttribute("data", products);
		model.addAttribute("link", "search/product/list/{keyword}/{pageNo}");
		model.addAttribute("isKeyword", true);
		//model.addAttribute("prdtList", products.size());
	
		return "Products";
    }
	
	//userId로 상품들 가져오기
	@RequestMapping("store/product/list")
    public String storeProductListAll(Model model){
		//List<Product> products = this.gachifarm.getAllProductByStore("DONGDUK01");
		//List<Product> products = this.gachifarm.getAllProductByStoreName("순복쓰 MARKET");
		List<Product> products = this.gachifarm.getProductBySupplier("김동덕");
		//model.addAttribute("data", "store/product/list");
        model.addAttribute("data", products);
        return "Products";
    }
	@GetMapping("product/{storeName}/product/list")
    public String storeProductList(Model model){
        model.addAttribute("data", "product/{storeName}/product/list");
        return "Store";
    }
	


}
