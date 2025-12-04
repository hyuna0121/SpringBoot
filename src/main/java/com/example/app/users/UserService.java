package com.example.app.users;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
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
	
	public boolean getError(UserDTO userDTO, BindingResult bindingResult) throws Exception {
		// check : true -> 검증 실패, error 존재 
		// check : false -> 검증 성공, error 존재 X 
		// 1. Annotation 검증 결과
		boolean check = bindingResult.hasErrors();
		
		// 2. password 일치하는 지 검증
		if (!userDTO.getPassword().equals(userDTO.getPasswordCheck())) {
			check = true;
//			bindingResult.rejectValue("멤버변수명", "properties의 키");
			bindingResult.rejectValue("passwordCheck", "user.password.equal");
		}
		
		// 3. id 중복 체크
		UserDTO checkDTO = userDAO.detail(userDTO);
		if (checkDTO != null) {
			check = true;
			bindingResult.rejectValue("username", "user.username.equal");
		}
		
		return check;
		
	}
	
}
