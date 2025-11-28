package com.example.app.board.notice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.board.BoardDAO;
import com.example.app.board.BoardDTO;
import com.example.app.board.BoardService;
import com.example.app.util.Pager;

@Service
public class NoticeService implements BoardService {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
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
	public int add(BoardDTO boardDTO) throws Exception {
		return noticeDAO.add(boardDTO);
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
