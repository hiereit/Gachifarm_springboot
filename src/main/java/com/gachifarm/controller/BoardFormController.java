package com.gachifarm.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.dao.ProductDao;
import com.gachifarm.domain.Board;
import com.gachifarm.domain.GroupProduct;
import com.gachifarm.domain.Product;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("product")
public class BoardFormController {
	@Autowired
	private GachiFarmFacade gachiFarm;

	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}
	
	@Autowired
	ProductDao productDao;
	
	@RequestMapping("/board/registerForm")
	public String newBoardForm(
			@RequestParam(name="productId", required=false, defaultValue= "0") int productId,
			Model model) {
		//UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		/*
		if (userSession == null) {
			return "로그인페이지";
		}*/
		productId = 2;
		if (productId != 0) {
			Product product = productDao.getProduct(productId);
			model.addAttribute("product", product);
		}
		model.addAttribute("board", new Board());
		return "Board/BoardForm";
	}
	
	@RequestMapping("/board/register")
	public String submit(@ModelAttribute("board") @Valid Board board, BindingResult result, HttpSession session, SessionStatus status, Model model) {
		Product product = (Product) session.getAttribute("product");
		if (result.hasErrors()) {
			model.addAttribute("product", product);
			return "Board/BoardForm";
		}
		board.setBoardDate(new Date());
		board.setUserId("DONGDUK02"); //세션에서 가져다 쓰기
		if (product != null) {
			board.setProductId(product.getProductId());
			status.setComplete();
		}
		gachiFarm.insertQuestion(board);
		//입력한 보드 페이지로 이동하게 바꾸기
		return "redirect:/board/"+board.getBoardId();
	}
}
