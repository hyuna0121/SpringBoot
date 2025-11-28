package com.example.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.board.BoardDTO;
import com.example.app.board.BoardService;
import com.example.app.util.Pager;

@Service
public class QnaService implements BoardService {

	@Autowired
	private QnaDAO qnaDAO;
	
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
	public int add(BoardDTO boardDTO) throws Exception {
		qnaDAO.add(boardDTO);
		int result = qnaDAO.refUpdate(boardDTO);
		
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO) throws Exception {
		return 0;
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		return 0;
	}
	
}
