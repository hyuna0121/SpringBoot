package com.example.app.board.qna;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QnaDTO {

	private Long boardNum;
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private LocalDate boardDate;
	private Long boardHit;
	private Long boardRef;
	private Long boardStep;
	private Long boardDepth;
	
}
