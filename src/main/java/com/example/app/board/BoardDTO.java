package com.example.app.board;

import java.time.LocalDate;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 게시판관련 DTO의 부모로 사용
@Setter
@Getter
@ToString
public class BoardDTO {
	
	private Long boardNum;
	
	@NotBlank
	private String boardTitle;
	private String boardWriter;
	private String boardContents;
	private LocalDate boardDate;
	private Long boardHit;
	
	private List<BoardFileDTO> fileDTOs;
	
}
