package com.gachifarm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.gachifarm.domain.Board;
import com.gachifarm.service.GachiFarmFacade;

@Controller
@SessionAttributes("userSession")
public class ViewBoardController {
	@Autowired
	private GachiFarmFacade gachiFarm;

	@RequestMapping("/board/{boardId}")
	public String viewGroupProduct(
			@PathVariable("boardId") int boardId, Model model, HttpSession session) throws Exception {
		Board board = gachiFarm.getBoardByBoardId(boardId);
		if (board.getProductId() != null) {
			board.setFilePath(gachiFarm.getImgPath(board.getProductId()));
		}
		if (session.getAttribute("userSession") == null) {
			model.addAttribute("board", board);
			model.addAttribute("isAdmin", false);
			model.addAttribute("isQST", false);
			return "Board/BoardDetail";
		}
		String userId = ((UserSession) session.getAttribute("userSession")).getAccount().getUserId();
		model.addAttribute("isAdmin", gachiFarm.isAdmin(userId));
		model.addAttribute("board", board);
		model.addAttribute("isQST", board.getUserId().equals(userId));
		return "Board/BoardDetail";
	}
	
	@RequestMapping("/board/{boardId}/delete")
	public String deleteBoard(
			@PathVariable("boardId") int boardId) throws Exception {
		gachiFarm.deleteBoard(boardId);
		return "redirect:/board/list/1";
	}
}
