package com.example.app.config.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		String message = "로그인 실패";
		
		if (exception instanceof AccountExpiredException) {
			message = "사용자 계정의 유효기간이 만료되었습니다.";
		} 
		
		if (exception instanceof LockedException) {
			message = "사용자 계정이 잠겨있습니다.";
		} 
		
		if (exception instanceof CredentialsExpiredException) {
			message = "비밀번호 유효 기간이 만료되었습니다.";
		} 
		
		if (exception instanceof DisabledException) {
			message = "휴면 사용자입니다.";
		}
		
		if (exception instanceof BadCredentialsException) {
			message = "잘못된 비밀번호입니다.";
		}
		
		if (exception instanceof InternalAuthenticationServiceException) {
			message = "존재하지 않는 사용자입니다.";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?message=" + message);
	}
	
}
