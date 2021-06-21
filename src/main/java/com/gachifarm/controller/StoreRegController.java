package com.gachifarm.controller;

import java.io.UnsupportedEncodingException;
import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriUtils;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Store;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class StoreRegController {

	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarmFacade(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}

	// Store등록 reuqest 처리
	@RequestMapping("store/registerForm")
	public String showStoreForm(Model model, HttpSession userSession) {

		Account sessionAccount = ((UserSession) userSession.getAttribute("userSession")).getAccount();
		if (gachifarm.getStore(sessionAccount.getUserId()) != null) {
				return "Main";
		}
		if(sessionAccount.getUserId().equals("admin")) {
			return "Main";
		}
		
		boolean isUpdate = false;
		model.addAttribute("isUpdate", isUpdate);
		
		model.addAttribute("storeCommand", new StoreRegRequest());

		return "Store/StoreForm";
		
	}

	@GetMapping("store/regist")
	public String returnStoreForm() {
		return "redirect:/store/list/all";
	}

	@PostMapping("store/regist")
	public String handleStoreForm(@Valid @ModelAttribute("storeCommand") StoreRegRequest regReq, BindingResult result,
			HttpSession userSession, Model model) throws NoResultException {
		// session에서 유저정보 가져오기
		Account sessionAccount = ((UserSession) userSession.getAttribute("userSession")).getAccount();

		// 스토어를 처음 만든다면..! >> 이미 있는 사람은 get에서 처리
		boolean isExistName = false;
		//model.addAttribute("isExistName", isExistName);

		// req에서 받아온 스토어 이름 당연히 없음!
		if (regReq.getStoreName() == null) {
			isExistName = false; // 그럼 중복되지 않은 거임!
			return "StoreForm";
		} else { // store객체가 있다? (req객체로 넘어오는 값이 있다) 두번째 이상
			if (gachifarm.getStoreName(regReq.getStoreName()) == null) // 근데 req객체로 부터 넘어오느 이름에 대한 store객체가 없다?
				isExistName = false; // 그럼 중복되지 않은 거임!
			else {
				isExistName = true; // 하지만, store객체가 넘어온다면 중복되는 거임
				model.addAttribute("isExistName", isExistName); // 다시 폼으로
				return "Store/StoreForm";
			}
		}
		model.addAttribute("isExistName", isExistName);

		if (result.hasErrors() || isExistName) {
			System.out.println("return 3$$$$$$$$$$$$");
			return "Store/StoreForm";
		}

		Store store = new Store(sessionAccount.getUserId(), regReq.getStoreName(), regReq.getStoreInfo());

		gachifarm.insertStore(store);
		return "redirect:/store/list";
	}

	// Store 정보 수정
	@RequestMapping("store/updateForm/{storeName}")
	public String showStoreUpdateForm(@ModelAttribute("storeCommand") StoreRegRequest regReq,
			Model model, HttpSession session, @PathVariable("storeName") String storeName) {
		Account sessionAccount = ((UserSession) session.getAttribute("userSession")).getAccount();

		Store store = gachifarm.getStore(sessionAccount.getUserId());
		boolean isUpdate = true;
		model.addAttribute("isUpdate", isUpdate);
		//model.addAttribute("storeCommnad", store);
		regReq.setStoreName(storeName);
		regReq.setStoreInfo(store.getStoreInfo());
		
		return "Store/StoreForm";
	}

	@GetMapping("store/update")
	public String returnStoreUpdateForm(@PathVariable("storeName") String storeName) {

		return "redirect:/store/updateForm/" + storeName;
	} 

	@PostMapping("store/update")
	public String handleStoreUpdateForm(@Valid @ModelAttribute("storeCommand") StoreRegRequest regReq,
			BindingResult result, HttpSession session, Model model) throws UnsupportedEncodingException {

		Account sessionAccount = ((UserSession) session.getAttribute("userSession")).getAccount();
		
		boolean isUpdate = true;
		model.addAttribute("isUpdate", isUpdate);
		
		
		if (result.hasErrors()) {
			model.addAttribute("storeCommand", regReq);
			return "Store/StoreForm";
		}
		// 상품 Home으로 이동!
		Store store = new Store(sessionAccount.getUserId(), regReq.getStoreName(), regReq.getStoreInfo());
		gachifarm.updateStore(store);
		
		String encodedParam = UriUtils.encodePathSegment(regReq.getStoreName(), "UTF-8");
		return "redirect:/store/" + encodedParam + "/1";
	}
}
