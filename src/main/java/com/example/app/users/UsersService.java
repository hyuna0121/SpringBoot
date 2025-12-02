package com.example.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.board.BoardFileDTO;
import com.example.app.files.FileManager;

@Service
public class UsersService {

	@Autowired
	private UsersDAO usersDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.users}")
	private String uploadPath;
	
	public int register(UsersDTO usersDTO, MultipartFile profile) throws Exception {
		int result = usersDAO.register(usersDTO);
		
		if (profile == null) {
			return result;
		}
		
		File file = new File(uploadPath);
		String fileName = fileManager.fileSave(file, profile);
					
		UsersFileDTO usersFileDTO = new UsersFileDTO();
		usersFileDTO.setFileName(fileName);
		usersFileDTO.setFileOrigin(profile.getOriginalFilename());
		usersFileDTO.setUsername(usersDTO.getUsername());
			
		usersDAO.userFileAdd(usersFileDTO);
		
		return result;
	}

	public UsersDTO detail(UsersDTO usersDTO) throws Exception {
		return usersDAO.detail(usersDTO);
	}
	
}
