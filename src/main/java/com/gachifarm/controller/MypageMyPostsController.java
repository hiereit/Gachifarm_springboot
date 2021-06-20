package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.Board;
import com.gachifarm.domain.LineProduct;
import com.gachifarm.domain.Review;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes({"userSession", "myorders"})
public class MypageMyPostsController {
	
	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping("user/mypage/myposts")
	public String myposts(HttpSession session, Model model, HttpServletRequest request) {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
//		Account account = (Account) session.getAttribute("account");
		Account account = userSession.getAccount();
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
	
}
