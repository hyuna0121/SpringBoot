package com.example.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.board.BoardDTO;
import com.example.app.board.notice.NoticeDTO;
import com.example.app.util.Pager;

@Controller
@RequestMapping("/qna/*")
public class QnaController {
	
	@Autowired
	private QnaService qnaService;
	
	@Value("${category.board.qna}")
	private String category;
	
	@ModelAttribute("category")
	public String getCategory() {
		return this.category;
	}
	
	@GetMapping("detail")
	public String detail(QnaDTO qnaDTO, Model model) throws Exception {
		qnaDTO = (QnaDTO) qnaService.detail(qnaDTO);
		
		if (qnaDTO.getBoardTitle() != null) {
			model.addAttribute("dto", qnaDTO);
		}
		
		return "/board/detail";
	}

	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		List<BoardDTO> qnaList = qnaService.list(pager);
		
		model.addAttribute("list", qnaList);
		model.addAttribute("pager", pager);
		
		return "/board/list";
	}
	
	@GetMapping("add")
	public String add(Model model) throws Exception {
		model.addAttribute("sub", "Add");
		
		return "/board/add";
	}
	
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
	
	@GetMapping("reply")
	public String reply(QnaDTO qnaDTO, Model model) throws Exception {
		model.addAttribute("dto", qnaDTO);
		
		return "/board/add";
	}
	
	@PostMapping("reply")
	public String replyProc(QnaDTO qnaDTO, Model model) throws Exception {
		int result = qnaService.reply(qnaDTO);
		
		String msg = "등록 실패";
		String path = "./list";
		if (result > 0) {
			msg = "등록 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "/commons/result";
	}
	
	@GetMapping("update")
	public String update(QnaDTO qnaDTO, Model model) throws Exception {
		qnaDTO = (QnaDTO) qnaService.detail(qnaDTO);
		
		if (qnaDTO.getBoardTitle() != null) {
			model.addAttribute("dto", qnaDTO);
			model.addAttribute("sub", "Update");
		}
		
		return "/board/add";
	}
	
	@PostMapping("update")
	public String updateProc(QnaDTO qnaDTO, Model model) throws Exception {
		int result = qnaService.update(qnaDTO);
		
		String msg = "수정 실패";
		String path = "./list";
		if (result > 0) {
			msg = "수정 성공";
		}
		
		model.addAttribute("path", path);
		model.addAttribute("msg", msg);
		
		return "/commons/result";
	}
	
	@PostMapping("delete")
	public String delete(QnaDTO qnaDTO) throws Exception {
		int result = qnaService.delete(qnaDTO);
		
		return "redirect:./list";
	}
	
}
