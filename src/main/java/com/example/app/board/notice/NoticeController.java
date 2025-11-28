package com.example.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("list")
	public void list(Pager pager, Model model) throws Exception {
		
		List<BoardDTO> noticeList = noticeService.list(pager);

		model.addAttribute("noticeList", noticeList);
		model.addAttribute("pager", pager);

	}
	
	@GetMapping("add")
	public void add() throws Exception { }
	
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
	public void detail(BoardDTO boardDTO, Model model) throws Exception {
		boardDTO = noticeService.detail(boardDTO);
		
		// null (조회 실패 시 처리) - 따로 추가하기
		
		if (boardDTO.getBoardTitle() != null) {
			model.addAttribute("notice", boardDTO);
		}
	}
	
}
