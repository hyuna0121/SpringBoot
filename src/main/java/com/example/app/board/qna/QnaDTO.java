package com.example.app.board.qna;

import com.example.app.board.BoardDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QnaDTO extends BoardDTO {

	private long boardRef;
	private long boardStep;
	private long boardDepth;
	
}
