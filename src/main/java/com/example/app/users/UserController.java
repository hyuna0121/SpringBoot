package com.example.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/users/*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("login")
	public void login() throws Exception { }
	
	@PostMapping("login")
	public String login(UserDTO userDTO, HttpSession session) throws Exception {
		userDTO = userService.detail(userDTO);
		
		session.setAttribute("user", userDTO);
		
		return "redirect:/";
	}
	
	@GetMapping("register")
	public void register(UserDTO userDTO) throws Exception { }
	
	@PostMapping("register")
	public String register(@Valid UserDTO userDTO, BindingResult bindingResult, MultipartFile profile, Model model) throws Exception {
		if (userService.getError(userDTO, bindingResult)) {
			
			return "/users/register";
		}
		
//		int result = userService.register(userDTO, profile);
//		
//		String msg = "가입 실패";
//		String path = "/";
//		if (result > 0) {
//			msg = "가입 성공";
//		}
//		
//		model.addAttribute("msg", msg);
//		model.addAttribute("path", path);
		
		return "/commons/result";
	}
	
	@GetMapping("mypage")
	public void mypage(UserDTO userDTO, Model model) throws Exception {
		userDTO = userService.detail(userDTO);
		model.addAttribute("dto", userDTO);
	}
	
}
