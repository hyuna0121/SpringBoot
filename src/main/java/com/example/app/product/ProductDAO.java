package com.example.app.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.util.Pager;

@Mapper
public interface ProductDAO {
	
	public ProductDTO detail(ProductDTO productDTO) throws Exception;
	
	public List<ProductDTO> list(Pager pager) throws Exception;
	
	public Long count(Pager pager) throws Exception;
	
	public int add(ProductDTO productDTO) throws Exception;
	
	public int delete(ProductDTO productDTO) throws Exception;
	
	public int update(ProductDTO productDTO) throws Exception;
	
	
	// 댓글
	public List<ProductCommentDTO> commentList(Map<String, Object> map) throws Exception;
	
	public int commentAdd(ProductCommentDTO productCommentDTO) throws Exception;
	
	public Long commentCount(ProductCommentDTO productCommentDTO) throws Exception;
	
	public int commentDelete(ProductCommentDTO productCommentDTO) throws Exception;
}
