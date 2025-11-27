package com.example.app.board.qna;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.util.Pager;

@Service
public class QnaService {

	@Autowired
	private QnaDAO qnaDAO;
	
	public QnaDTO detail(QnaDTO qnaDTO) throws Exception {
		return qnaDAO.detail(qnaDTO);
	}
	
	public List<QnaDTO> list(Pager pager) throws Exception {
		Long totalCount = qnaDAO.count(pager);
		
		pager.paging(totalCount);
		
		return qnaDAO.list(pager);
	}
	
	public int add(QnaDTO qnaDTO) throws Exception {
		qnaDAO.add(qnaDTO);
		int result = qnaDAO.refUpdate(qnaDTO);
		
		return result;
	}
	
}
