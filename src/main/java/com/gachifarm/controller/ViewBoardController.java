package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachifarm.domain.Board;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ViewBoardController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("/board/{boardId}")
	public String viewGroupProduct(
			@PathVariable("boardId") int boardId, Model model) throws Exception {
		model.addAttribute("isAdmin", gachiFarm.isAdmin("admin"));
		Board board = gachiFarm.getBoardByBoardId(boardId);
		model.addAttribute("board", board);
		model.addAttribute("isQST", board.getUserId().equals("admin"));
		return "Board/BoardDetail";
	}
	
	@RequestMapping("/board/{boardId}/delete")
	public String deleteBoard(
			@PathVariable("boardId") int boardId) throws Exception {
		gachiFarm.deleteBoard(boardId);
		return "redirect:/board/list/1";
	}
}
