package com.example.app.board.notice;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.util.Pager;

@Mapper
public interface NoticeDAO {
	
	public Long count(Pager pager) throws Exception;

	public NoticeDTO detail(NoticeDTO noticeDTO) throws Exception;
	
	public List<NoticeDTO> list(Pager pager) throws Exception; 
	
	public int add(NoticeDTO noticeDTO) throws Exception;
	
	public int update(NoticeDTO noticeDTO) throws Exception;
	
	public int delete(NoticeDTO noticeDTO) throws Exception;
	
}
