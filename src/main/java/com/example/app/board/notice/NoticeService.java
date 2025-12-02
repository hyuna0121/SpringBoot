package com.example.app.board.notice;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.board.BoardDAO;
import com.example.app.board.BoardDTO;
import com.example.app.board.BoardFileDTO;
import com.example.app.board.BoardService;
import com.example.app.files.FileManager;
import com.example.app.util.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.notice}")
	private String uploadPath;
	
	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		// 1. totalCount 구하기
		Long totalCount = noticeDAO.count(pager);
		
		pager.paging(totalCount);
		
		return noticeDAO.list(pager);
	}
	
	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return noticeDAO.detail(boardDTO);
	}
	
	@Override
	public int add(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
		int result = noticeDAO.add(boardDTO);
		
		if (attach == null) {
			return result;
		}
		
		// 1. 파일을 HDD에 저장
		//   1) 어디에 저장?
		//   2) 어떤 이름으로 저장?
		File file = new File(uploadPath);
		for (MultipartFile f : attach) {
			if (f == null || f.isEmpty()) { continue; }
			
			String fileName = fileManager.fileSave(file, f);
					
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setFileOrigin(f.getOriginalFilename());
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			
			noticeDAO.fileAdd(boardFileDTO);
		}
		
		return result;
	}
	
	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return noticeDAO.update(boardDTO);
	}
	
	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		return noticeDAO.delete(boardDTO);
	}
	
}
