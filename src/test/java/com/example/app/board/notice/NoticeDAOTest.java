package com.example.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional()
class NoticeDAOTest {
	
	@Autowired
	private NoticeDAO noticeDAO;
	
//	@Test
//	void testCount() throws Exception {
//		Long count = noticeDAO.count();
//		
//		System.out.println(count);
//	}
	
//	@Test
//	void testDelete() throws Exception {
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setBoardNum(3L);
//		
//		int result = noticeDAO.delete(noticeDTO);
//		assertEquals(1, result);
//	}
	
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
	
	@Test
	@Rollback(false)
	void testAdd() throws Exception {
		
		for (int i = 0; i < 120; i++) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setBoardTitle("title" + i);
			noticeDTO.setBoardWriter("writer" + i);
			noticeDTO.setBoardContents("contents" + i);
			
			noticeDAO.add(noticeDTO);
			
			if (i % 10 == 0) Thread.sleep(500);
		}
		
	}
	
//	@Test
//	void testList() throws Exception {
//		List<NoticeDTO> noticeList = noticeDAO.list();
//		
//		assertNotEquals(0, noticeList.size());
//	}
//
//	@Test
//	void testDetail() throws Exception {
//		NoticeDTO noticeDTO = new NoticeDTO();
//		noticeDTO.setBoardNum(1L);
//		
//		noticeDTO = noticeDAO.detail(noticeDTO);
//		
//		assertNotNull(noticeDTO);
//	}

}
