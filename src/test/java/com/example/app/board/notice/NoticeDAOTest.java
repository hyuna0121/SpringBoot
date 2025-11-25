package com.example.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
	@Test
	void testDelete() throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(3L);
		
		int result = noticeDAO.delete(noticeDTO);
		assertEquals(1, result);
	}
	
//	@Test
//	void testUpdate() throws Exception {
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setBoardNum(2L);
//		noticeDTO.setBoardTitle("UPDATE TEST");
//		noticeDTO.setBoardContents("update test");
//		
//		int result = noticeDAO.update(noticeDTO);
//		assertEquals(1, result);
//	}
	
//	@Test
//	void testAdd() throws Exception {
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setBoardTitle("INSERT TEST");
//		noticeDTO.setBoardWriter("lha");
//		noticeDTO.setBoardContents("insert test");
//		
//		int result = noticeDAO.add(noticeDTO);
//		assertEquals(1, result);
//	}
	
	@Test
	void testList() throws Exception {
		List<NoticeDTO> noticeList = noticeDAO.list();
		
		assertNotEquals(0, noticeList.size());
	}

	@Test
	void testDetail() throws Exception {
		NoticeDTO noticeDTO = new NoticeDTO();
		noticeDTO.setBoardNum(1L);
		
		noticeDTO = noticeDAO.detail(noticeDTO);
		
		assertNotNull(noticeDTO);
	}

}
