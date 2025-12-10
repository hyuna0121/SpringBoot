package com.example.app.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.app.users.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	@Autowired
	private LoginFailHandler loginFailHandler;
	@Autowired
	private LogoutSuccess logoutSuccess;
	@Autowired
	private Logout logoutHandler;
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	// 정적 자원들을 Security에서 제외
	@Bean
	WebSecurityCustomizer customizer() {
		return web -> {
			web
				.ignoring()
				.requestMatchers("/css/**")
				.requestMatchers("/js/**", "/vendor/**")
				.requestMatchers("/img/**", "/images/**");
		};
	}
	
	// 인증과 인가에 관한 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception {
		security
			.cors((cors) -> {cors.disable();})
			.csrf((csrf) -> {csrf.disable();})
		
			// 인가(권한)에 관한 설정
			.authorizeHttpRequests((auth) -> {
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
					.requestMatchers("/product/add", "/product/update", "/product/delete").hasAnyRole("ADMIN", "MANAGER")
					.requestMatchers("/product/**").authenticated()
					.requestMatchers("/users/mypage", "/users/update", "/users/logout").authenticated()
					.anyRequest().permitAll();			
			})
			
			// Login form과 그 외 관련 설정
			.formLogin((form) -> {
				form
					// 로그인폼 jsp 경로로 가는 url과 로그인 처리 url 작성
					.loginPage("/users/login")
					.loginProcessingUrl("/users/login") // 로그인을 진행할 Url
					// .usernameParameter("id")
					// .passwordParameter("pw") // DB이름과 다를 경우에 사용
					// .defaultSuccessUrl("/");
					// .failureUrl("") // 실패할 경우 처리
					.successHandler(loginSuccessHandler)
					.failureHandler(loginFailHandler);
					
			})
			.logout((logout) -> {
				logout
					.logoutUrl("/users/logout")
					// .logoutSuccessUrl("/")
					.addLogoutHandler(logoutHandler)
					.logoutSuccessHandler(logoutSuccess)
					.invalidateHttpSession(true)
					.deleteCookies("JSESSIONID", "remember-me");
			})
			.rememberMe(remember -> {
				remember
					.rememberMeParameter("rememberme") // html의 name값
					//.tokenValiditySeconds(60)
					.key("rememberkey")
					.userDetailsService(detailsServiceImpl) // spring login과정에서 실제로 호출되는 userDetailService를 넣음
					.authenticationSuccessHandler(loginSuccessHandler)
					.useSecureCookie(true);
			})
			.sessionManagement(session -> {
				session
					.invalidSessionUrl("/")
					.maximumSessions(1)
					.maxSessionsPreventsLogin(true)
					.expiredUrl("/");
			})
			;
		
		return security.build();
			
	}
	
	@Bean
	PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
