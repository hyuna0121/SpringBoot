package com.example.app.board;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class BoardDAOTest {
	
	@Autowired
	private BoardDAO boardDAO;
	
	@Test
	void testUpdate() throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("n", 35L);
		map.put("t", "Update Test2");
		map.put("c", "Update Test2");
		
		int result = boardDAO.update(map);
		assertEquals(1, result);
	}
	
//	@Test
//	void testDelete() throws Exception {
//		int result = boardDAO.delete(55L);
//		assertEquals(1, result);
//	}
	
	@Test
	void testList() throws Exception {
		List<BoardDTO> ar = boardDAO.list();
		
		assertNotEquals(0, ar.size());
	}

//	@Test
//	void testAdd() throws Exception {
//		BoardDTO boardDTO = new BoardDTO();
//		boardDTO.setTitle("Add test");
//		boardDTO.setWriter("lha");
//		boardDTO.setContents("Add test");
//		
//		int result = boardDAO.add(boardDTO);
//		assertEquals(1, result);
//	}

//	@Test
//	void testDetail() throws Exception {
//		BoardDTO boardDTO = new BoardDTO();
//		boardDTO.setNum(20L);
//		
//		boardDTO = boardDAO.detail(boardDTO);
//		// log.info(boardDTO.toString());
//		
//		assertNotNull(boardDTO); // null이 아니면 성공
//	}

}
