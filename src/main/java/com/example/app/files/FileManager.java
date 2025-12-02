package com.example.app.files;

import java.io.File;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileManager {

	// TDD : 에어를 발생시키면서 코드를 작성하는 기법
	public String fileSave(File file, MultipartFile f) throws Exception {
		if (!file.exists()) { file.mkdirs(); }
		
		// 저장할 파일명 생성(확장자 포함)
		String fileName = UUID.randomUUID().toString();
		fileName = fileName + "_" + f.getOriginalFilename();
		
		// HDD에 파일 저장
		file = new File(file, fileName);
		
		// 방법1
		// FileCopyUtils.copy(f.getBytes(), file);
		
		// 방법2
		f.transferTo(file);
		
		return fileName;
	}
}
