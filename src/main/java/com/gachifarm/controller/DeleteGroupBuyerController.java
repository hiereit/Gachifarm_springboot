package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;
import org.springframework.ui.Model;

@Controller
@SessionAttributes({"userSession", "myorders"})
public class DeleteGroupBuyerController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	

	
	@PostMapping(value = "/user/mypage/mygroup/orders")
	public String updateQty(HttpSession session, Model model, HttpServletRequest request) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!POSTMAPPING!!!!!!!!!!!!!!!!!");
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
//		Account account = (Account) session.getAttribute("account");
		Account account = userSession.getAccount();
		
			//GroupProduct테이블 curr수정
			int gpId = Integer.parseInt(request.getParameter("gpId"));
			int nowQtyInt = Integer.parseInt(request.getParameter("nowQty"));
			System.out.println("gpIdInt: " + gpId + ", nowQtyInt: " + nowQtyInt);
			GroupProduct gp = gachiFarm.findGroupProductBygProductId(gpId);
			System.out.println(gp.toString());
			int currQty = gp.getCurrQty();
			gp.setCurrQty(currQty - nowQtyInt);	//현재상황에 취소된 공구 수 뺴줌
			gachiFarm.save(gp);
			
			//GroupBuyer테이블 qty수정
			GroupBuyer gb = gachiFarm.findGroupBuyersByUserIdAndGroupProductId(account.getUserId(), gpId);
			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!GB: " + gb);
			gachiFarm.delete(gb);
			System.out.println("공구 취소됨!!!!");
			

			System.out.println("변경: " + gp.toString());
			System.out.println("currQty: " + currQty);


	
		
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

		return "redirect:";
	}
}
