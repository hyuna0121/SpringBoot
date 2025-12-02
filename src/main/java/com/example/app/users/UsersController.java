package com.example.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/users/*")
public class UsersController {

	@Autowired
	private UsersService usersService;
	
	@GetMapping("register")
	public void register() throws Exception { }
	
	@PostMapping("register")
	public String register(UsersDTO usersDTO, MultipartFile profile, Model model) throws Exception {
		int result = usersService.register(usersDTO, profile);
		
		String msg = "가입 실패";
		String path = "/";
		if (result > 0) {
			msg = "가입 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "/commons/result";
	}
	
	@GetMapping("mypage")
	public void mypage(UsersDTO usersDTO, Model model) throws Exception {
		usersDTO = usersService.detail(usersDTO);
		model.addAttribute("dto", usersDTO);
		
	}
	
}
