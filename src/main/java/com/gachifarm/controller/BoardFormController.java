package com.gachifarm.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
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
			return 
		}*/
		productId = 1;
		Board board = new Board();
		if (productId != 0) {
			board.setProduct(productDao.getProduct(productId));
			board.setProductId(productId);
		}
		model.addAttribute("board", board);
		return "Board/BoardForm";
	}
	
	@RequestMapping("/board/register")
	public String submit(@ModelAttribute("board") @Valid Board board, BindingResult result, HttpServletRequest request, Model model) {
		int productId = board.getProductId()==null?0:board.getProductId();
		if (productId != 0) {
			board.setProduct(productDao.getProduct(productId));
		}
		if (result.hasErrors()) {
			return "Board/BoardForm";
		}
		board.setBoardDate(new Date());
		board.setUserId("DONGDUK01");
		gachiFarm.saveBoard(board);
		return "redirect:/board/"+board.getBoardId();
	}
	
	@RequestMapping("/board/{boardId}/updateForm")
	public String updateBoardForm(
			@PathVariable("boardId") int boardId,
			Model model) {
		//UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		/*
		if (userSession == null) {
			return 
		}*/
		Board board = gachiFarm.getBoardByBoardId(boardId);
		if (!board.getUserId().equals("DONGDUK01")) {
			// 세션에서 꺼내기
			return "redirect:/board/list/1";
		}
		model.addAttribute("board", board);
		return "Board/BoardUpdateForm";
	}

	@RequestMapping("/board/update")
	public String update(@ModelAttribute("board") @Valid Board board, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			int productId = board.getProductId()==null?0:board.getProductId();
			board.setProduct(productDao.getProduct(productId));
			return "Board/BoardUpdateForm";
		}
		Board updateBoard = gachiFarm.getBoardByBoardId(board.getBoardId());
		updateBoard.setTitle(board.getTitle());
		updateBoard.setQuestion(board.getQuestion());
		updateBoard.setBoardPW(board.getBoardPW());
		gachiFarm.saveBoard(updateBoard);
		return "redirect:/board/"+board.getBoardId();
	}

	@RequestMapping("/board/answer")
	public String answer(@ModelAttribute("board") @Valid Board board, BindingResult result, Model model) {
		if (result.hasErrors()) {
			int boardId = board.getBoardId();
			String boardAnswer = board.getAnswer();
			board = gachiFarm.getBoardByBoardId(boardId);
			board.setAnswer(boardAnswer);
			model.addAttribute("isAdmin", gachiFarm.isAdmin("admin"));
			model.addAttribute("isQST", board.getUserId().equals("admin"));
			return "Board/BoardDetail";
		}
		Board updateBoard = gachiFarm.getBoardByBoardId(board.getBoardId());
		updateBoard.setAnswer(board.getAnswer());
		gachiFarm.saveBoard(updateBoard);
		return "redirect:/board/"+board.getBoardId();
	}
}
