package com.example.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.board.BoardDTO;
import com.example.app.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@GetMapping("detail")
	public void detail(BoardDTO boardDTO, Model model) throws Exception {
		boardDTO = qnaService.detail(boardDTO);
		
		if (boardDTO.getBoardTitle() != null) {
			model.addAttribute("qna", boardDTO);
		}
	}

	@GetMapping("list")
	public void list(Pager pager, Model model) throws Exception {
		List<BoardDTO> qnaList = qnaService.list(pager);
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("pager", pager);
	}
	
	@GetMapping("add")
	public void add() throws Exception { }
	
	@PostMapping("add")
	public String add(QnaDTO qnaDTO, Model model) throws Exception {
		int result = qnaService.add(qnaDTO);
		
		String msg = "등록 실패";
		String path = "./list";
		if (result > 0) {
			msg = "등록 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "/commons/result";
	}
}
