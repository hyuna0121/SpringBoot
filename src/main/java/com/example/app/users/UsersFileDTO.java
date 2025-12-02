package com.example.app.users;

import com.example.app.files.FileDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsersFileDTO extends FileDTO {
	
	private String username;
	
}
