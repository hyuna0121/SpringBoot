package com.example.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;

	@GetMapping("list")
	public void list(Pager pager, Model model) throws Exception {
		List<QnaDTO> qnaList = qnaService.list(pager);
		
		model.addAttribute("qnaList", qnaList);
		model.addAttribute("pager", pager);
	}
	
}
