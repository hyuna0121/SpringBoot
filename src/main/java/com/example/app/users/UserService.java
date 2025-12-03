package com.example.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.board.BoardFileDTO;
import com.example.app.files.FileManager;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.users}")
	private String uploadPath;
	
	public int register(UserDTO userDTO, MultipartFile profile) throws Exception {
		int result = userDAO.register(userDTO);
		
		if (profile == null) {
			return result;
		}
		
		File file = new File(uploadPath);
		String fileName = fileManager.fileSave(file, profile);
					
		UserFileDTO usersFileDTO = new UserFileDTO();
		usersFileDTO.setFileName(fileName);
		usersFileDTO.setFileOrigin(profile.getOriginalFilename());
		usersFileDTO.setUsername(userDTO.getUsername());
			
		userDAO.userFileAdd(usersFileDTO);
		
		return result;
	}

	public UserDTO detail(UserDTO userDTO) throws Exception {
		UserDTO loginDTO = userDAO.detail(userDTO);
		
		if (loginDTO != null) {
			if (loginDTO.getPassword().equals(userDTO.getPassword())) {
				return loginDTO;
			} else {
				loginDTO = null;
			}
		}
		
		return loginDTO;
	}
	
}
