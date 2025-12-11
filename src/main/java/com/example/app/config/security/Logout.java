package com.example.app.config.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import reactor.core.publisher.Mono;

@Component
public class Logout implements LogoutHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String restKey;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		System.out.println("logout handler");
		if (authentication == null) return;
		// kakao 서버로 로그아웃 요청을 보냄
		// 서비스에서만 로그아웃, 세션이 남음
//		WebClient webClient = WebClient.create();
//		
//		Mono<String> res = webClient
//			.post()
//			.uri("https://kapi.kakao.com/v1/user/logout")
//			.header("Authorization", "KakaoAK " + adminKey)
//			.header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
//			.body(BodyInserters.fromFormData("target_id_type", "user_id").with("target_id", authentication.getName()))
//			.retrieve()
//			.bodyToMono(String.class);
//		
//		System.out.println(res.block());
		
		// 카카오계정과 함께 로그아웃
		try {
			response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id=" + restKey + "&logout_redirect_uri=http://localhost/"); // redirect : GET 방식
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
	}

}
