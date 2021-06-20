package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.CartPK;
import com.gachifarm.service.GachiFarmFacade;
@Controller
@SessionAttributes("userSession")
public class RemovePrdtFromCartController {
	private GachiFarmFacade gachifarm;

	@Autowired
	public void setGachiFarm(GachiFarmFacade gachifarm) {
		this.gachifarm = gachifarm;
	}

	@RequestMapping("/cart/delete")
	@ResponseBody
	public Object removeCartProduct(@RequestParam(value="delArray[]") List<String> delList, HttpSession userSession) throws Exception {
		String userId = ((UserSession) userSession.getAttribute("userSession")).getAccount().getUserId();
		List<CartPK> cartIdList = new ArrayList<CartPK>();
		for (String del : delList) {
			cartIdList.add(new CartPK(userId, Integer.parseInt(del)));
		}
		gachifarm.deleteCart(cartIdList);
		Map<String, Object> retVal = new HashMap<String, Object>();
        retVal.put("ret", "OK");
        return retVal;
	}
}
