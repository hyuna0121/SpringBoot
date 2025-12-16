package com.example.app.config.security.jwt;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	
	private AuthenticationManager authenticationManager;
	
	private JwtTokenManager tokenManager;
	
	public JwtLoginFilter(JwtTokenManager tokenManager, AuthenticationManager authenticationManager) {
		this.tokenManager = tokenManager;
		this.authenticationManager = authenticationManager;
		this.setFilterProcessesUrl("/users/loginProcess");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		System.out.println("로그인 시도");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		// UserDetailService의 loadUserByusername 메서드를 호출하고
		// 패스워드가 일치하는 지 판별하고 Authentication 객체를 
		// SecurityContextHolder에 넣어줌
		
		
		return authenticationManager.authenticate(authenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		String accessToken = tokenManager.makeAccessToken(authResult);
		String refreshToken = tokenManager.makeRefreshToken(authResult);
		
		Cookie cookie = new Cookie("access-token", accessToken);
		cookie.setPath("/");
		cookie.setMaxAge(60);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		
		cookie = new Cookie("refresh-token", refreshToken);
		cookie.setPath("/");
		cookie.setMaxAge(600);
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
		
		response.sendRedirect("/");
	}
	
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		// 로그인 실패
		System.out.println(failed.getMessage());
		
		String message = "로그인 실패";
		
		if (failed instanceof AccountExpiredException) {
			message = "사용자 계정의 유효기간이 만료되었습니다.";
		} 
		
		if (failed instanceof LockedException) {
			message = "사용자 계정이 잠겨있습니다.";
		} 
		
		if (failed instanceof CredentialsExpiredException) {
			message = "비밀번호 유효 기간이 만료되었습니다.";
		} 
		
		if (failed instanceof DisabledException) {
			message = "휴면 사용자입니다.";
		}
		
		if (failed instanceof BadCredentialsException) {
			message = "잘못된 비밀번호입니다.";
		}
		
		if (failed instanceof InternalAuthenticationServiceException) {
			message = "존재하지 않는 사용자입니다.";
		}
		
		message = URLEncoder.encode(message, "UTF-8");
		response.sendRedirect("./login?message=" + message);
	}
	
}
