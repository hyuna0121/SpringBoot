package com.example.app.product;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductDTO {
	
	private Long productNum;
	private String productName;
	private String productContents;
	private String productCategory;
	private Double productRate;
	private Boolean productSale;
	
}
