package com.gachifarm.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.CartPK;
import com.gachifarm.repository.CartRepository;
@Controller
@SessionAttributes("userSession")
public class RemovePrdtFromCartController {
	@Autowired
	private CartRepository cartRepository;

	@RequestMapping("/cart/delete")
	@ResponseBody
	public Object removeCartProduct(@RequestParam(value="delArray[]") List<String> delList) throws Exception {
		//인터셉터에 넣을 것
		//String userId = userSession.getAccount().getUserId();
		String userId = "DONGDUK01";
		List<CartPK> cartIdList = new ArrayList<CartPK>();
		for (String del : delList) {
			cartIdList.add(new CartPK(userId, Integer.parseInt(del)));
		}
		cartRepository.deleteAllById(cartIdList);
		Map<String, Object> retVal = new HashMap<String, Object>();
        retVal.put("code", "OK");
        return retVal;
 
	}
}
