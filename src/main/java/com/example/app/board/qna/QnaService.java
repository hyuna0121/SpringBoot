package com.example.app.board.qna;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.app.board.BoardDTO;
import com.example.app.board.BoardFileDTO;
import com.example.app.board.BoardService;
import com.example.app.files.FileManager;
import com.example.app.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
	@Autowired
	private FileManager fileManager;
	
	@Value("${app.upload.qna}")
	private String uploadPath;
	
	@Override
	public BoardDTO detail(BoardDTO boardDTO) throws Exception {
		return qnaDAO.detail(boardDTO);
	}
	
	@Override
	public List<BoardDTO> list(Pager pager) throws Exception {
		Long totalCount = qnaDAO.count(pager);
		
		pager.paging(totalCount);
		
		return qnaDAO.list(pager);
	}
	
	@Override
	public int add(BoardDTO boardDTO, MultipartFile[] attach) throws Exception {
		int result = qnaDAO.add(boardDTO);
		qnaDAO.refUpdate(boardDTO);
		
		if (attach == null) {
			return result;
		}
		
		File file = new File(uploadPath);
		for (MultipartFile f : attach) {
			if (f == null || f.isEmpty()) {
				continue;
			}
			
			String fileName = fileManager.fileSave(file, f);
			
			BoardFileDTO boardFileDTO = new BoardFileDTO();
			boardFileDTO.setFileName(fileName);
			boardFileDTO.setFileOrigin(f.getOriginalFilename());
			boardFileDTO.setBoardNum(boardDTO.getBoardNum());
			
			qnaDAO.fileAdd(boardFileDTO);
		}
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		boardDTO = qnaDAO.detail(boardDTO);
		
		if (boardDTO.getFileDTOs() != null) {
			for (BoardFileDTO boardFileDTO : boardDTO.getFileDTOs()) {
				File file = new File(uploadPath, boardFileDTO.getFileName());
				boolean flag = fileManager.fileDelete(file);
			}
			
			qnaDAO.fileDelete(boardDTO);
		}
		
		return qnaDAO.delete(boardDTO);
	}
	
	public int reply(QnaDTO qnaDTO) throws Exception {
		// 1. 부모글의 정보 조회(ref, step, depth)
		QnaDTO parent = (QnaDTO) qnaDAO.detail(qnaDTO);
		
		// 2. 부모글의 정보를 이용해서 step을 업데이트
		qnaDAO.stepUpdate(parent);
		
		// 3. 부모글의 정보를 이용해서 나머지 ref, step, depth를 세팅하고
		qnaDTO.setBoardRef(parent.getBoardRef());
		qnaDTO.setBoardStep(parent.getBoardStep() + 1);
		qnaDTO.setBoardDepth(parent.getBoardDepth() + 1);
		
		// 4. insert
		return qnaDAO.add(qnaDTO);
	}
	
}
