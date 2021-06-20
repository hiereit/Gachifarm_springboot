package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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
import com.gachifarm.domain.Board;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Orders;
import com.gachifarm.domain.Review;
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

	@ModelAttribute(name="signupCommand")
	public SignupCommand formBacking() {
		return new SignupCommand();
	}

	@GetMapping
	public String mypage(HttpSession session, Model model) {

		Account sessionAccount = (Account) session.getAttribute("account");
		SignupCommand signupCommand = new SignupCommand(
				sessionAccount.getUserId(), sessionAccount.getPassword(), 
				sessionAccount.getUserName(), sessionAccount.getPhone(),
				sessionAccount.getEmail(), sessionAccount.getZip(),
				sessionAccount.getAddr1(), sessionAccount.getAddr2()
				);
		
		model.addAttribute("account", sessionAccount);
		model.addAttribute("signupCommand", signupCommand);
		
		System.out.println("MypageController - " + sessionAccount.getUserId());

		return "Account/MypageUpdateSignupForm";
	}

	
	@PostMapping
	public String updateAccount(@Valid @ModelAttribute("signupCommand") SignupCommand signupCommand, BindingResult result, HttpSession session, Model model) {
		System.out.println("==========================" + signupCommand.getPhone());

		String password = signupCommand.getPassword();
		System.out.println("updateAccount() - password: " + password);

		if(result.hasErrors()) {
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
			System.out.println(result.toString());
			if(!signupCommand.isPasswordEqualToConfirmPassword()) {
				System.out.println("!불일치!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
				String str = "비밀번호 불일치";
				model.addAttribute("str", str);

				return "Account/MypageUpdateSignupForm";
			}

			return "Account/MypageUpdateSignupForm";
		}
		
		String phone = signupCommand.getPhone();
		System.out.println("updateAccount() - phone: " + phone);
		String email = signupCommand.getEmail();
		String zip = signupCommand.getZip();
		String addr1 = signupCommand.getAddr1();
		String addr2 = signupCommand.getAddr2();

		System.out.println("updateAccount() - " + session.getAttribute("account").toString());		
		Account account = (Account) session.getAttribute("account");

		account.setUserId(signupCommand.getUserId());
		account.setUserName(signupCommand.getUserName());
		
		account.setPassword(password);
		account.setPhone(phone);
		account.setEmail(email);
		account.setZip(zip);
		account.setAddr1(addr1);
		account.setAddr2(addr2);
		System.out.println("updateAccount: " + account);
		
		model.addAttribute("changeAccount", account);
		gachiFarm.save(account);
		System.out.println("수정완료!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		String str = "수정이 완료되었습니다";
		model.addAttribute("str2", str);

		return "Account/MypageUpdateSignupForm";
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
		
		return "Account/MypageMyOrders";
	}
	
	@GetMapping("/myposts")
	public String myposts(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		List<Board> boardList = gachiFarm.findBoardByUserId(account.getUserId());
		System.out.println("myposts() - boardList: " + boardList);
		String [] answerStatus = new String[boardList.size()];
		if(boardList != null) {
			for(int i = 0; i < boardList.size(); i++) {
				if(boardList.get(i).getAnswer() == null) {
					answerStatus[i] = "NO";
				}
				else {
					answerStatus[i] = "YES";
				}
			}
			
			model.addAttribute("boardList", boardList);
			model.addAttribute("answerStatus", answerStatus);
		}
		
		List<Review> reviewList = gachiFarm.findReviewByUserId(account.getUserId());
		List<LineProduct> lineProduct = new ArrayList<LineProduct>();
		int [] linePrdtId = new int[reviewList.size()];
		System.out.println("myposts() - reviewList: " + reviewList);
		if(boardList != null) {
			for(int i = 0; i < reviewList.size(); i++) {
				linePrdtId[i] = reviewList.get(i).getLineProductId();
				lineProduct.add(i, gachiFarm.findByLineProductId(linePrdtId[i]));
			}
			System.out.println("myposts() - lineProduct: " + lineProduct);
			model.addAttribute("lineProduct", lineProduct);
			model.addAttribute("reviewList", reviewList);
		}
		
		return "Account/MyPageMyBoardAndReview";
	}
	
	@GetMapping("/mygroup/open")
	public String myopengroups(HttpSession session, Model model) {
		Account account = (Account) session.getAttribute("account");
		List<GroupProduct> gpList = gachiFarm.findGroupProductByUserId(account.getUserId());
//		System.out.println(gpList);
		if(gpList != null) {
			model.addAttribute("groupProducts", gpList);
		}

		return "Account/MypageMyOpenGroup";
	}
	
	@GetMapping("/mygroup/orders")
	public String myparticipategroups(HttpSession session, Model model, HttpServletRequest request) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!GETMAPPING!!!!!!!!!!!!!!!!!");
		Account account = (Account) session.getAttribute("account");
		List<GroupBuyer> gbList = gachiFarm.findGroupBuyersByUserId(account.getUserId());
		int [] gPrdtId = new int[gbList.size()];

		System.out.println(gbList);
		List<GroupProduct> gpList = new ArrayList<GroupProduct>();
		if(gbList != null) {
			for(int i = 0; i < gbList.size(); i++) {
				gPrdtId[i] = gbList.get(i).getGroupProductId();
				System.out.println("gPrdtId[" + i + "]: " + gPrdtId[i]);
				System.out.println(gachiFarm.findGroupProductBygProductId(gPrdtId[i]));
				gpList.add(i, gachiFarm.findGroupProductBygProductId(gPrdtId[i]));
			}
			model.addAttribute("groupProducts", gpList);
			model.addAttribute("groupBuyers", gbList);
		}

		return "Account/MypageMyParticipateGroup";
	}
}