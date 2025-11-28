package com.example.app.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.app.util.Pager;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("list")
	public void list(Pager pager, Model model) throws Exception {
		List<ProductDTO> productList = productService.list(pager);
		
		model.addAttribute("productList", productList);
	}
	
	@GetMapping("add")
	public void add() throws Exception { }
	
	@PostMapping("add")
	public String add(ProductDTO productDTO, Model model) throws Exception {
		int result = productService.add(productDTO);
		
		String msg = "등록 실패";
		String path = "./list";
		if (result > 0) {
			msg = "등록 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "/commons/result";
	}
	
}
