package com.example.app.product;

import java.util.List;

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
	
}
