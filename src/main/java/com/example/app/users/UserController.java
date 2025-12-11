package com.example.app.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/users/*")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	@GetMapping("login")
	public String login(HttpSession session) throws Exception {
		Object obj = session.getAttribute("SPRING_SECURITY_CONTEXT");
		
		if (obj != null) {
			return "redirect:/";
		}
		
		return "users/login";
	}
	
	@GetMapping("register")
	public void register(UserDTO userDTO) throws Exception { }
	
	@PostMapping("register")
	public String register(@Validated(RegisterGroup.class) UserDTO userDTO, BindingResult bindingResult, MultipartFile profile, Model model) throws Exception {
		if (userService.getError(userDTO, bindingResult)) {
			
			return "users/register";
		}
		
		int result = userService.register(userDTO, profile);
		
		String msg = "가입 실패";
		String path = "/";
		if (result > 0) {
			msg = "가입 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "commons/result";
	}
	
	@GetMapping("mypage")
	public void mypage(@AuthenticationPrincipal UserDTO userDTO, Model model) throws Exception {
		userDTO = userService.detail(userDTO);
		model.addAttribute("user", userDTO);
	}
	
	@GetMapping("update")
	public void update(@AuthenticationPrincipal UserDTO userDTO, Model model) throws Exception {
		model.addAttribute("userDTO", userDTO);
	}
	
	@PostMapping("update")
	public String update(@Validated(UpdateGroup.class) UserDTO userDTO, Authentication authentication, BindingResult bindingResult, MultipartFile profile, Model model) throws Exception {
		if (bindingResult.hasErrors()) {
			return "users/register";
		}
		
		userDTO.setUsername(authentication.getName());
		int result = userService.update(userDTO, profile);
		
		String msg = "수정 실패";
		String path = "./mypage";
		if (result > 0) {
			// DB에서 사용자를 조회해야 함
//			UsernamePasswordAuthenticationToken to = new UsernamePasswordAuthenticationToken(userDTO, authentication.getCredentials(), authentication.getAuthorities());
//			SecurityContextHolder.getContext().setAuthentication(to);
			msg = "수정 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "commons/result";
	}
	
	@GetMapping("change")
	public void change(UserDTO userDTO) throws Exception { }
	
	@PostMapping("change")
	public String change(@Validated(PasswordGroup.class) UserDTO userDTO, BindingResult bindingResult, String exist) throws Exception {
		if (userService.getError(userDTO, bindingResult)) {
			return "users/change";
		}
		
		return "redirect:mypage";
	}
	
	@GetMapping("delete")
	public String delete(Authentication authentication) throws Exception {
		// 1. 일반회원
		
		// 로그아웃 진행
		
		// 2. 소셜 로그인
		// DB에서 작업
		
		WebClient webClient = WebClient.create();
		Mono<String> result = webClient
			.post()
			.uri("https://kapi.kakao.com/v1/user/unlink")
			.header("Authorization", "KakaoAK " + adminKey)
			.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
			.body(BodyInserters.fromFormData("target_id_type", "user_id").with("target_id", authentication.getName()))
			.retrieve()
			.bodyToMono(String.class);
		System.out.println(result.block());
		
		return "redirect:./logout";
	}
	
}
