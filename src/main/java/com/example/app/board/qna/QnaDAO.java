package com.example.app.board.qna;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.board.BoardDAO;
import com.example.app.board.BoardDTO;

@Mapper
public interface QnaDAO extends BoardDAO  {
	
	public int refUpdate(BoardDTO boardDTO) throws Exception;
	
}
