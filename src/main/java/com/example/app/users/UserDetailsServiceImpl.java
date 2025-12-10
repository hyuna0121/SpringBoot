package com.example.app.users;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserDetailsServiceImpl extends DefaultOAuth2UserService implements UserDetailsService {
	
	@Autowired
	private UserDAO userDAO;

	// Filter에서 호출됨
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("로그인 요청");
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(username);
		UserDetails userDetails;
		
		try {
			userDetails = userDAO.detail(userDTO);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UsernameNotFoundException(username);
		}
		
		return userDetails;
	} // UserDetailsService
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		String sns = userRequest.getClientRegistration().getClientName();
		log.info(sns);
		
		UserDTO user = null;
		if (sns.toUpperCase().equals("KAKAO")) {
			user = this.useKakao(userRequest);
			user.setSns(sns);
		}
		
		return user;
	} // DefaultOAuth2UserService
	
	private UserDTO useKakao(OAuth2UserRequest userRequest) {
		OAuth2User user = super.loadUser(userRequest);
		
		log.info("name : {}", user.getName());
		log.info("attr : {}", user.getAttributes());
		log.info("auth : {}", user.getAuthorities());
		
		Map<String, Object> attr = user.getAttribute("properties");
		String name = attr.get("nickname").toString();
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername(user.getName());
		try {
			userDTO = userDAO.detail(userDTO);
			if (userDTO == null) {
				userDTO = new UserDTO();
				userDTO.setUsername(user.getName());
				userDTO.setPassword("kakao");
				userDTO.setName(name);
				userDAO.register(userDTO);
				
				UserFileDTO userFileDTO = new UserFileDTO();
				userFileDTO.setFileName(attr.get("profile_image").toString());
				userFileDTO.setUsername(user.getName());
				userDAO.userFileAdd(userFileDTO);
				
				userDTO.setUserFileDTO(userFileDTO);
				
				userDAO.roleAdd(userDTO);
				
				userDTO = userDAO.detail(userDTO);
			}
			
			// 로그인성공 또는 가입 후 공통 진행
			userDTO.setAttributes(attr);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return userDTO;
	}
	
}
