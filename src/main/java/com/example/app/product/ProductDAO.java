package com.example.app.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.app.util.Pager;

@Mapper
public interface ProductDAO {
	
	public List<ProductDTO> list(Pager pager) throws Exception;
	
	public Long count(Pager pager) throws Exception;
	
	public int add(ProductDTO productDTO) throws Exception;
	
}
