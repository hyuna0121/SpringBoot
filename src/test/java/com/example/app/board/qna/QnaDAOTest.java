package com.example.app.board.qna;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class QnaDAOTest {

	@Autowired
	private QnaDAO qnaDAO;
	
//	@Test
//	void testList() throws Exception {
//		List<QnaDTO> qnaList = qnaDAO.list();
//		assertNotEquals(0, qnaList.size());
//	}

	@Test
	void testAdd() throws Exception {
		for (int i = 99; i < 101; i++) {
			QnaDTO qnaDTO = new QnaDTO();
			qnaDTO.setBoardTitle("INSERT TEST" + i);
			qnaDTO.setBoardWriter("admin");
			qnaDTO.setBoardContents("INSERT TEST" + i);
			
			qnaDAO.add(qnaDTO);
			if (i % 10 == 0) Thread.sleep(500);
		}
	}

}
