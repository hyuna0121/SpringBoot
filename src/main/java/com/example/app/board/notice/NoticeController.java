package com.example.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.app.board.BoardDTO;
import com.example.app.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice/*")
@Slf4j
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Value("${category.board.notice}")
	private String category;
	
	@ModelAttribute("category")
	public String getCategory() {
		return this.category;
	}
	
	@GetMapping("list")
	public String list(Pager pager, Model model) throws Exception {
		
		List<BoardDTO> noticeList = noticeService.list(pager);

		model.addAttribute("list", noticeList);
		model.addAttribute("pager", pager);
		
		return "/board/list";
	}
	
	@GetMapping("add")
	public String add(Model model) throws Exception { 
		model.addAttribute("sub", "Add");
		
		return "/board/add";
	}
	
	@PostMapping("add")
	public String add(NoticeDTO noticeDTO, Model model) throws Exception {
		int result = noticeService.add(noticeDTO);
		
		String msg = "등록 실패";
		String path = "./list";
		if (result > 0) {
			msg = "등록 성공";
		}
		
		model.addAttribute("path", path);
		model.addAttribute("msg", msg);
		
		return "/commons/result";
//		return "redirect:./list"
	}
	
	@GetMapping("detail")
	public String detail(BoardDTO boardDTO, Model model) throws Exception {
		boardDTO = noticeService.detail(boardDTO);
		
		// null (조회 실패 시 처리) - 따로 추가하기
		
		if (boardDTO.getBoardTitle() != null) {
			model.addAttribute("dto", boardDTO);
		}
		
		return "/board/detail";
	}
	
	@GetMapping("update")
	public String update(NoticeDTO noticeDTO, Model model) throws Exception {
		noticeDTO = (NoticeDTO) noticeService.detail(noticeDTO);
		
		if (noticeDTO.getBoardTitle() != null) {
			model.addAttribute("dto", noticeDTO);
			model.addAttribute("sub", "Update");
		}
		
		return "/board/add";
	}
	
	@PostMapping("update")
	public String updateProc(NoticeDTO noticeDTO, Model model) throws Exception {
		int result = noticeService.update(noticeDTO);
		
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
	public String delete(NoticeDTO noticeDTO) throws Exception {
		int result = noticeService.delete(noticeDTO);
		
		return "redirect:./list";
	}
	
}
