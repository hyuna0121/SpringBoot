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
		return qnaDAO.update(boardDTO);
	}

	@Override
	public int delete(BoardDTO boardDTO) throws Exception {
		return qnaDAO.delete(boardDTO);
	}
	
	public int reply(QnaDTO qnaDTO) throws Exception {
		// 1. 부모글의 정보 조회(ref, step, depth)
		QnaDTO parent = (QnaDTO) qnaDAO.detail(qnaDTO);
		
		// 2. 부모글의 정보를 이용해서 step을 업데이트
		qnaDAO.stepUpdate(parent);
		
		// 3. 부모글의 정보를 이용해서 나머지 ref, step, depth를 세팅하고
		qnaDTO.setBoardRef(parent.getBoardRef());
		qnaDTO.setBoardStep(parent.getBoardStep() + 1);
		qnaDTO.setBoardDepth(parent.getBoardDepth() + 1);
		
		// 4. insert
		return qnaDAO.add(qnaDTO);
	}
	
}
