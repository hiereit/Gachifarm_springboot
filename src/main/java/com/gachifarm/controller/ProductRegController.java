package com.gachifarm.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ProductRegController {
		private GachiFarmFacade gachifarm;
		
		

	@Autowired
	public void setGachiFarmFacade(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}
	
	@RequestMapping("product/registerForm")
	public String showForm(Model model) {
		model.addAttribute("productCommand", new ProductRegRequest());
		return "ProductForm";
	}
	
	@GetMapping("product/regist")
	public String returnRegisterForm() {
		///
		return "redirect:/product/registerForm";
	}
	
	@PostMapping("product/regist")
	public String handleForm(
			@ModelAttribute("productCommand") ProductRegRequest regReq, 
			@RequestParam(value = "img_upload", required = false) MultipartFile file, Model model) throws IOException {
		//여기 원본 코드
		Product product = new 
				Product(regReq.getPrice(),regReq.getOrigin(), regReq.getSupplier(), regReq.getUnit(), 
				'y', regReq.getQuantity(), regReq.getDescription(), "DONGDUK02", "GACHI", regReq.getCategory(),
				regReq.getPrdtName());
	
		
		gachifarm.insertProduct(product);
		
		//추가 코드
		System.out.println(System.getProperty("user.dir") + "/src/main/resources/static/images");
		
		final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images"; 
		
		File convertFile = new File(FILE_PATH, file.getOriginalFilename());
		file.transferTo(convertFile);
		
		
		//String imgPath = FILE_PATH + "\\" +file.getOriginalFilename();
		String imgPath = "/images/" + file.getOriginalFilename();
		
		//productImg 저장하는 코드
		ProductImage pImg = new ProductImage(regReq.getPrdtName(), 
				imgPath, gachifarm.getProductByName(regReq.getPrdtName()).getProductId());
		
		gachifarm.insertProductImage(pImg);			
		
		
		String message = file.getOriginalFilename() + "is saved in server db";
		model.addAttribute("message", message);
		return "test";
		
		//return new ModelAndView("main");
	}
	
	@RequestMapping("product/updateForm/{productId}")
	public String showUpdateForm(@PathVariable("productId") int productId, Model model) {
		Product product = gachifarm.getProduct(productId);
		
		String link;
		if(gachifarm.getProductImageByPid(productId) == null) {
			link = "https://dummyimage.com/500x500/ffffff/000000.png&text=preview+image";
		}
		else {
			link = gachifarm.getProductImageByPid(productId).getImgPath();
		}
		System.out.println(link);
		//model.addAttribute("productCommand", new ProductRegRequest());
		model.addAttribute("productCommand", product);
		model.addAttribute("upPrdtId", productId);
		model.addAttribute("link", link);
		
		return "ProductUpdateForm";
	}
	@GetMapping("product/update")
	public String returnUpdateForm() {
		///
		return "redirect:/product/updateForm";
	}
	@PostMapping("product/update")
	public String handleUpdateForm(
			@ModelAttribute("productCommand") ProductRegRequest regReq,  
			Model model, HttpServletRequest rq) throws IOException {
		//여기 원본 코드
		String userID = gachifarm.getProductByName(regReq.getPrdtName()).getUserId();
		int prdtId = gachifarm.getProductByName(regReq.getPrdtName()).getProductId();
		int pid = Integer.parseInt(rq.getParameter("up_prdtId"));
		
		Product product = new Product(pid, regReq.getPrice(),regReq.getOrigin(), regReq.getSupplier(), regReq.getUnit(), 
				'y', regReq.getQuantity(), regReq.getDescription(), userID, "GACHI", regReq.getCategory(),regReq.getPrdtName());
		
		gachifarm.updateProduct(product);
		model.addAttribute("message", rq.getParameter("up_prdtId"));
		
		return "test";
	}
}
