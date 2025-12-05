package com.example.app.product;

import com.example.app.board.CommentDTO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductCommentDTO extends CommentDTO {

	private Long productNum;
	private String username;
	
}
