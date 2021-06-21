package com.gachifarm.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gachifarm.domain.Board;
import com.gachifarm.service.GachiFarmFacade;

@Controller
public class ListBoardController {
	@Autowired
	private GachiFarmFacade gachiFarm;

	@RequestMapping("/board/list/{pageNo}")
    public String boardListAll(@PageableDefault Pageable pageable,
    		@PathVariable("pageNo") int pageNo, Model model, HttpSession session){
		int count = 15;
		Page<Board> boardPage = gachiFarm.getBoardListbyPage(pageable, pageNo, count);
		List<Board> boardList = boardPage.getContent();
		model.addAttribute("boardPage", boardPage);
		model.addAttribute("boardList", boardList);
		if (session.getAttribute("userSession") == null) {
			model.addAttribute("isAdmin", false);
			model.addAttribute("count", count);
			return "Board/BoardList";
		}
		String userId = ((UserSession) session.getAttribute("userSession")).getAccount().getUserId();
		model.addAttribute("isAdmin", gachiFarm.isAdmin(userId));
		model.addAttribute("count", count);
		return "Board/BoardList";
    }
	
	@RequestMapping("/product/{productId}/board/list/{pageNo}")
    public String productBoardList(@PageableDefault Pageable pageable, @PathVariable("productId") int productId,
    		@PathVariable("pageNo") int pageNo, Model model, HttpSession session){
		int count = 10;
		Page<Board> boardPage = gachiFarm.getBoardListbyPageAndProductId(pageable, pageNo, count, productId);
		List<Board> boardList = boardPage.getContent();
		model.addAttribute("boardPage", boardPage);
		model.addAttribute("boardList", boardList);
		String userId = ((UserSession) session.getAttribute("userSession")).getAccount().getUserId();
		model.addAttribute("isAdmin", gachiFarm.isAdmin(userId));
		model.addAttribute("count", count);
		return "Board/BoardList";
    }
}
