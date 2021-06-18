package com.gachifarm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.service.GachiFarmFacade;
@Controller
@RequestMapping("/user/mypage")
@SessionAttributes({"account", "myorders"})
public class MypageController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@ModelAttribute(name="changeAccount")
	public SignupCommand formBacking() {
		return new SignupCommand();
	}

	@GetMapping
	public String mypage(HttpSession session, Model model) {

		Account sessionAccount = (Account) session.getAttribute("account");
		model.addAttribute("account", sessionAccount);

		System.out.println(sessionAccount.getUserId());
		return "Account/MypageLayout";

	}
	
	@PostMapping
	public String updateAccount(@ModelAttribute("changeAccount") SignupCommand changeAccount, HttpSession session, Model model, BindingResult result) {

		String password = changeAccount.getPassword();
		System.out.println("updateAccount() - password: " + password);
		if(!changeAccount.isPasswordEqualToConfirmPassword()) {
			String str = "비밀번호 불일치";
			model.addAttribute("str", str);
			return "Account/MypageLayout";
		}
		
		String phone = changeAccount.getPhone();
		System.out.println("updateAccount() - phone: " + phone);
		String email = changeAccount.getEmail();
		String zip = changeAccount.getZip();
		String addr1 = changeAccount.getAddr1();
		String addr2 = changeAccount.getAddr2();

		System.out.println("updateAccount() - " + session.getAttribute("account").toString());		
		Account account = (Account) session.getAttribute("account");

		account.setPassword(password);
		account.setPhone(phone);
		account.setEmail(email);
		account.setZip(zip);
		account.setAddr1(addr1);
		account.setAddr2(addr2);
		System.out.println("updateAccount: " + account);
		
		model.addAttribute("account", account);
		gachiFarm.save(account);
		System.out.println("수정완료!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String str = "수정이 완료되었습니다";
		model.addAttribute("str2", str);
		return "Account/MypageLayout";
	}
	
	@GetMapping("/myorders")
	public String myorders(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		List<Orders> orderList = gachiFarm.findOrdersByUserId(account.getUserId());
		System.out.println("myorders()-orderList: " + orderList);
		String[] productNameByOrderId = new String[orderList.size()];
		long[] countByOrderId = new long[orderList.size()];
		System.out.println("배열길이(orderId수):" + countByOrderId.length);

		if(orderList != null) {
			for(int i = 0; i < orderList.size(); i++) {
				int orderId = orderList.get(i).getOrderId();
				countByOrderId[i] = gachiFarm.countByOrderId(orderId);
				
				System.out.println(gachiFarm.findTop1ProductNameByOrderId(orderId));
				if(gachiFarm.findTop1ProductNameByOrderId(orderId) != null) {
					productNameByOrderId[i] = gachiFarm.findTop1ProductNameByOrderId(orderId).getProductName();
					System.out.println(productNameByOrderId[i]);
				}
			}

			model.addAttribute("productNamesArray", productNameByOrderId);
			model.addAttribute("countsArray", countByOrderId);
			model.addAttribute("orders", orderList);
		}
		
		return "Account/MypageLayout";
	}
	
	@GetMapping("/myborders")
	public String myborders(HttpSession session, Model model) {
	
		
		return "Account/MypageLayout";
	}
	
	@GetMapping("/myopengroups")
	public String myopengroups(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		List<GroupProduct> gpList = gachiFarm.findGroupProductByUserId(account.getUserId());
		System.out.println("==========================================================================");
		System.out.println(gpList);
		if(gpList != null) {
			model.addAttribute("groupProducts", gpList);
		}
		return "Account/MypageLayout";
	}
	
	@GetMapping("/mygroups")
	public String mygroups(HttpSession session, Model model) {
		
		
		return "Account/MypageLayout";
	}
}