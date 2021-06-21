package com.gachifarm.controller;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Product;
import com.gachifarm.domain.ProductImage;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
public class ProductRegController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarmFacade(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}

	// product 등록 폼으로 가기
	@RequestMapping("product/registerForm")
	public String showForm(Model model) {
		model.addAttribute("productCommand", new ProductRegRequest());
		return "Product/ProductForm";
	}

	@GetMapping("product/regist")
	public String returnRegisterForm() {
		return "redirect:/product/registerForm";
	}

	// 성공 및 입력값 검증.
	@PostMapping("product/regist")
	public String handleForm(HttpSession userSession, @Valid @ModelAttribute("productCommand") ProductRegRequest regReq,
			BindingResult result, @RequestParam(value = "img_upload", required = false) MultipartFile file, Model model)
			throws IOException {
		// 가치팜 상품 데이터 등록

		boolean isUpdate = false; // 업데이트 인가 아닌가.
		model.addAttribute("isUpdate", isUpdate);

		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();

		// Account sessionAccount = (Account) session.getAttribute("account");

		// System.out.println("----------1"+ regReq.toString()+"1---------");
		if (result.hasErrors()) {
			model.addAttribute("productCommand", regReq);
			System.out.println("----------2" + regReq.toString() + "2---------");
			System.out.println("----------result.hasErrors()---------");
			return "Product/ProductForm";
		}

		Product product;
		if (userId.equals("admin")) {
			// 여기 원본 코드
			product = new Product(regReq.getPrice(), regReq.getOrigin(), regReq.getSupplier(), regReq.getUnit(), 'y',
					regReq.getQuantity(), regReq.getDescription(), "관리자", "GACHI", regReq.getCategory(),
					regReq.getPrdtName());

		} else {
			product = new Product(regReq.getPrice(), regReq.getOrigin(), regReq.getSupplier(), regReq.getUnit(), 'y',
					regReq.getQuantity(), regReq.getDescription(), userId, "STORE", regReq.getCategory(),
					regReq.getPrdtName());
		}
		gachifarm.insertProduct(product);

		// 추가 코드
		System.out.println(System.getProperty("user.dir") + "/src/main/resources/static/images");

		final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";

		String imgPath;
		File convertFile = new File(FILE_PATH, file.getOriginalFilename());
		try {
			file.transferTo(convertFile);
			imgPath = "/images/" + file.getOriginalFilename();
		} catch (Exception e) {
			imgPath = "\\images\\noImage.png";
		}

		// productImg 저장하는 코드
		System.out.println(regReq.getPrdtName() + "-------------------");
		System.out.println(regReq + "-------------------");
		if (!regReq.getPrdtName().equals("")) {
			ProductImage pImg = new ProductImage(regReq.getPrdtName(), imgPath, product.getProductId());
//				imgPath, gachifarm.getProductByName(regReq.getPrdtName()).getProductId());

			gachifarm.insertProductImage(pImg);
		}

		String message = file.getOriginalFilename() + "is saved in server db";
		model.addAttribute("message", message);
		
		if(userId.equals("admin")) {
			return "redirect:/product/list/all/1";
		}
		else {
			String encodedParam = URLEncoder.encode(gachifarm.getStore(userId).getStoreName(), "UTF-8");
			//return "redirect:/store/" + encodedParam + "/1";
			return "redirect:/store/" + encodedParam + "/1";
		}
		
	}

	@RequestMapping("store/product/registerForm/{storeName}")
	public String handleStorePrdtForm(@ModelAttribute("productCommand") ProductRegRequest regReq,
			@PathVariable("storeName") String storeName,
			@RequestParam(value = "img_upload", required = false) MultipartFile file, Model model) throws IOException {
		// 여기 원본 코드
		Product product = new Product(regReq.getPrice(), regReq.getOrigin(), regReq.getSupplier(), regReq.getUnit(),
				'y', regReq.getQuantity(), regReq.getDescription(), "DONGDUK02", "STORE", regReq.getCategory(),
				regReq.getPrdtName());

		gachifarm.insertProduct(product);

		// 추가 코드
		System.out.println(System.getProperty("user.dir") + "/src/main/resources/static/images");

		final String FILE_PATH = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\images";

		File convertFile = new File(FILE_PATH, file.getOriginalFilename());
		file.transferTo(convertFile);

		// String imgPath = FILE_PATH + "\\" +file.getOriginalFilename();
		String imgPath = "/images/" + file.getOriginalFilename();

		// productImg 저장하는 코드
		ProductImage pImg = new ProductImage(regReq.getPrdtName(), imgPath,
				gachifarm.getProductByName(regReq.getPrdtName()).getProductId());

		gachifarm.insertProductImage(pImg);

		String message = file.getOriginalFilename() + "is saved in server db";
		model.addAttribute("message", message);

		return "Product/ProductForm";

		// return new ModelAndView("main");
	}

	@RequestMapping("product/updateForm/{productId}")
	public String showUpdateForm(@ModelAttribute("productCommand") ProductRegRequest regReq,
			@PathVariable("productId") int productId, Model model) {
		Product product = gachifarm.getProduct(productId);
		ProductImage pImg = gachifarm.getProductImageByPid(productId);

		System.out.println(product);
		System.out.println(pImg);

		boolean isUpdate = true;
		model.addAttribute("isUpdate", isUpdate);
		model.addAttribute("upPrdtId", productId);
		String link;
		if (gachifarm.getProductImageByPid(productId) == null) {
			link = "https://dummyimage.com/500x500/ffffff/000000.png&text=preview+image";
		} else {
			link = gachifarm.getProductImageByPid(productId).getImgPath();
			regReq.setImgPath(pImg.getImgPath());
			regReq.setProductImg(pImg.getImgName()); // imgId? imgName?
		}
		regReq.setCategory(product.getCategory());
		regReq.setDescription(product.getDescription());

		regReq.setOrigin(product.getOrigin());
		regReq.setPrdtName(product.getPrdtName());
		regReq.setPrice(product.getPrice());

		regReq.setQuantity(product.getQuantity());
		regReq.setSupplier(product.getSupplier());
		regReq.setUnit(product.getUnit());

		System.out.println(link);
		// model.addAttribute("productCommand", new ProductRegRequest());
		// model.addAttribute("productCommand", product);
		model.addAttribute("upPrdtId", productId);
		model.addAttribute("link", link);

		return "Product/ProductUpdateForm";
	}

	@GetMapping("product/update")
	public String returnUpdateForm() {
		///
		return "Product/ProductUpdateForm";
	}

	@PostMapping("product/update")
	public String handleUpdateForm(@Valid @ModelAttribute("productCommand") ProductRegRequest regReq,
			BindingResult result, Model model, HttpServletRequest rq, HttpSession userSession) throws IOException {

		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();

		boolean isUpdate = true;
		model.addAttribute("isUpdate", isUpdate);
		model.addAttribute("upPrdtId", rq.getParameter("up_prdtId"));

		// System.out.println("----------1"+ regReq.toString()+"1---------");
		if (result.hasErrors()) {
			model.addAttribute("productCommand", regReq);
			System.out.println("----------2" + regReq.toString() + "2---------");
			System.out.println("----------result.hasErrors()---------");
			return "Product/ProductUpdateForm";
		}
		// 여기 원본 코드
		int pid = Integer.parseInt(rq.getParameter("up_prdtId"));
		Product temp = gachifarm.getProduct(pid);

		Product product = new Product(pid, regReq.getPrice(), regReq.getOrigin(), regReq.getSupplier(),
				regReq.getUnit(), 'y', regReq.getQuantity(), regReq.getDescription(), userId, temp.getSaleType(),
				regReq.getCategory(), regReq.getPrdtName());

		gachifarm.updateProduct(product);
		model.addAttribute("message", rq.getParameter("up_prdtId"));

		return "Main";
	}
}
