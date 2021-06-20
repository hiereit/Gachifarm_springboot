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

import com.gachifarm.domain.Account;
import com.gachifarm.domain.GroupBuyer;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes({"account", "myorders"})
public class MypageMyParticipateGroupsController {

	UserSession userSession;

	GachiFarmFacade gachiFarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@GetMapping("user/mypage/mygroup/orders")
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
