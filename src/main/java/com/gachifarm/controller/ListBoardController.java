package com.gachifarm.controller;

import java.util.List;

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
	public void setGachiFarm(GachiFarmFacade gachiFarm) {
		this.gachiFarm = gachiFarm;
	}

	@RequestMapping("/board/list/{pageNo}")
    public String boardListAll(@PageableDefault Pageable pageable,
    		@PathVariable("pageNo") int pageNo, Model model){
		int count = 15;
		Page<Board> boardPage = gachiFarm.getBoardListbyPage(pageable, pageNo, count);
		List<Board> boardList = boardPage.getContent();
		model.addAttribute("boardPage", boardPage);
		model.addAttribute("boardList", boardList);
		model.addAttribute("isAdmin", gachiFarm.isAdmin("DONGDUK01")); //!!! session에서 꺼내기
		model.addAttribute("count", count);
		return "Board/BoardList";
    }
	
	@RequestMapping("/product/{productId}/board/list/{pageNo}")
    public String productBoardList(@PageableDefault Pageable pageable, @PathVariable("productId") int productId,
    		@PathVariable("pageNo") int pageNo, Model model){
		int count = 10;
		Page<Board> boardPage = gachiFarm.getBoardListbyPageAndProductId(pageable, pageNo, count, productId);
		List<Board> boardList = boardPage.getContent();
		model.addAttribute("boardPage", boardPage);
		model.addAttribute("boardList", boardList);
		model.addAttribute("isAdmin", gachiFarm.isAdmin("DONGDUK01")); //!!! session에서 꺼내기
		model.addAttribute("count", count);
		return "Board/BoardList";
    }
}
