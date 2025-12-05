package com.example.app.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.app.util.Pager;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO;
	
	public ProductDTO detail(ProductDTO productDTO) throws Exception {
		return productDAO.detail(productDTO);
	}
	
	public List<ProductDTO> list(Pager pager) throws Exception {
		Long totalCount = productDAO.count(pager);
		
		pager.paging(totalCount);
		
		return productDAO.list(pager);
	}
	
	public int add(ProductDTO productDTO) throws Exception {
		return productDAO.add(productDTO);
	}
	
	public int delete(ProductDTO productDTO) throws Exception {
		return productDAO.delete(productDTO);
	}
	
	public int update(ProductDTO productDTO) throws Exception {
		return productDAO.update(productDTO);
	}
	
	
	// 댓글
	public List<ProductCommentDTO> commentList(ProductCommentDTO productCommentDTO, Pager pager) throws Exception {
		pager.setPerPage(5L);
		Map<String, Object> map = new HashMap<>();
		map.put("product", productCommentDTO);
		map.put("pager", pager);
		
		Long totalCount = productDAO.commentCount(productCommentDTO);
		pager.paging(totalCount);
		
		return productDAO.commentList(map);
	}
	
	public int commentAdd(ProductCommentDTO productCommentDTO) throws Exception{
		return productDAO.commentAdd(productCommentDTO);
	}
	
	public int commentDelete(ProductCommentDTO productCommentDTO) throws Exception {
		return productDAO.commentDelete(productCommentDTO);
	}
	
}
