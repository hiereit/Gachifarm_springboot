package com.gachifarm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ViewBoardController {
	@Autowired
	private GachiFarmFacade gachiFarm;
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("/board/{boardId}")
	public ModelAndView viewGroupProduct(
			@PathVariable("boardId") int boardId) throws Exception {
		return new ModelAndView("Board/BoardDetail", "board", gachiFarm.getBoardByBoardId(boardId));
	}
}
