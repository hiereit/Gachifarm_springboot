package com.gachifarm.controller;

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

import com.gachifarm.domain.Board;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class BoardFormController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	
	@RequestMapping("/board/registerForm")
	public String newBoardForm(
			@RequestParam(name="productId", required=false, defaultValue= "0") int productId,
			Model model) {
		
		Board board = new Board();
		if (productId != 0) {
			board.setProduct(gachiFarm.getProduct(productId));
			board.setProductId(productId);
			board.setFilePath(gachiFarm.getImgPath(productId));
		}
		model.addAttribute("board", board);
		return "Board/BoardForm";
	}
	
	@RequestMapping("/board/register")
	public String submit(@ModelAttribute("board") @Valid Board board, BindingResult result, Model model, HttpSession session) {
		int productId = board.getProductId()==null?0:board.getProductId();
		if (productId != 0) {
			board.setProduct(gachiFarm.getProduct(productId));
			board.setFilePath(gachiFarm.getImgPath(productId));
		}
		if (result.hasErrors()) {
			return "Board/BoardForm";
		}
		String userId = ((UserSession) session.getAttribute("userSession")).getAccount().getUserId();
		board.setUserId(userId);
		gachiFarm.saveBoard(board);
		return "redirect:/board/"+board.getBoardId();
	}
	
	@RequestMapping("/board/{boardId}/updateForm")
	public String updateBoardForm(
			@PathVariable("boardId") int boardId, HttpSession session,
			Model model) {
		Board board = gachiFarm.getBoardByBoardId(boardId);
		if (board.getProductId() != null) {
			board.setFilePath(gachiFarm.getImgPath(board.getProductId()));
		}
		String userId = ((UserSession) session.getAttribute("userSession")).getAccount().getUserId();
		if (!board.getUserId().equals(userId)) {
			return "redirect:/board/list/1";
		}
		model.addAttribute("board", board);
		return "Board/BoardUpdateForm";
	}

	@RequestMapping("/board/update")
	public String update(@ModelAttribute("board") @Valid Board board, BindingResult result) {
		if (result.hasErrors()) {
			int productId;
			if (board.getProductId() != null) {
				productId = board.getProductId();
				board.setFilePath(gachiFarm.getImgPath(productId));
				board.setProduct(gachiFarm.getProduct(productId));
			}
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
	public String answer(@ModelAttribute("board") @Valid Board board, BindingResult result, Model model, HttpSession session) {
		String userId = ((UserSession) session.getAttribute("userSession")).getAccount().getUserId();
		if (result.hasErrors()) {
			int boardId = board.getBoardId();
			String boardAnswer = board.getAnswer();
			board = gachiFarm.getBoardByBoardId(boardId);
			board.setAnswer(boardAnswer);
			model.addAttribute("isAdmin", gachiFarm.isAdmin(userId));
			model.addAttribute("isQST", board.getUserId().equals(userId));
			return "Board/BoardDetail";
		}
		Board updateBoard = gachiFarm.getBoardByBoardId(board.getBoardId());
		updateBoard.setAnswer(board.getAnswer());
		gachiFarm.saveBoard(updateBoard);
		return "redirect:/board/"+board.getBoardId();
	}
}
