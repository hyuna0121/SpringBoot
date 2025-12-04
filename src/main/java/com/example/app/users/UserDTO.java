package com.example.app.users;

import java.time.LocalDate;

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
public class UserDTO {

	@NotBlank
	private String username;
	
	//@Pattern(regexp = "^(?=.*[!@#$%^&*])[A-Za-z\\d!@#$%^&*]{8,12}$")
	private String password;
	
	private String passwordCheck;
	
	@NotBlank
	private String name;
	
	@Email
	@NotBlank
	private String email;
	
	//@Pattern(regexp = "^01(?:0|1|[6-9])-(?:\\d{3}|\\d{4})-\\d{4}$")
	private String phone;
	
	@Past
	private LocalDate birth;
	
	private UserFileDTO userFileDTO;
	
}
