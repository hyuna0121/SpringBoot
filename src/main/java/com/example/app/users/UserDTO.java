package com.example.app.users;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserDTO implements UserDetails {

	@NotBlank(groups = {RegisterGroup.class})
	private String username;
	
	@NotBlank(groups = {RegisterGroup.class, PasswordGroup.class})
	//@Pattern(regexp = "^(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,12}$")
	private String password;
	
	private String passwordCheck;
	
	@NotBlank(groups = {RegisterGroup.class, UpdateGroup.class})
	private String name;
	
	@Email(groups = {RegisterGroup.class, UpdateGroup.class})
	@NotBlank(groups = {RegisterGroup.class})
	private String email;
	
	@Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$", groups = {RegisterGroup.class, UpdateGroup.class})
	private String phone;
	
	@Past(groups = {RegisterGroup.class, UpdateGroup.class})
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birth;
	
	private UserFileDTO userFileDTO;
	
	private List<RoleDTO> roleDTOs;
	
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;
	

	// 인가
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> list = new ArrayList<>();
		
		for (int i = 0; i < roleDTOs.size(); i++) {
			GrantedAuthority g = new SimpleGrantedAuthority(roleDTOs.get(i).getRoleName());
			list.add(g);
		}
		
		return list;
	}

}
