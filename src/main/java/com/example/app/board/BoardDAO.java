package com.example.app.board;

import java.util.List;

import com.example.app.util.Pager;

public interface BoardDAO {

	public Long count(Pager pager) throws Exception;

	public BoardDTO detail(BoardDTO boardDTO) throws Exception;
	
	public List<BoardDTO> list(Pager pager) throws Exception; 
	
	public int add(BoardDTO boardDTO) throws Exception;
	
	public int update(BoardDTO boardDTO) throws Exception;
	
	public int delete(BoardDTO boardDTO) throws Exception;
	
	public int fileAdd(BoardFileDTO boardFileDTO) throws Exception;
}
