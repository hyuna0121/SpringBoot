package com.example.app.product;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.app.users.UserDTO;
import com.example.app.util.Pager;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("detail")
	public void detail(ProductDTO productDTO, Model model) throws Exception {
		productDTO = productService.detail(productDTO);
		
		model.addAttribute("product", productDTO);
	}
	
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
		
		return "commons/result";
	}
	
	@PostMapping("delete")
	public String delete(ProductDTO productDTO, Model model) throws Exception {
		int result = productService.delete(productDTO);
		
		String msg = "삭제 실패";
		String path = "./list";
		if (result > 0) {
			msg = "삭제 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "commons/result";
	}
	
	@GetMapping("update")
	public String update(ProductDTO productDTO, Model model) throws Exception {
		productDTO = productService.detail(productDTO);
		
		model.addAttribute("product", productDTO);
		
		return "product/add";
	}
	
	@PostMapping("update")
	public String updateProc(ProductDTO productDTO, Model model) throws Exception {
		int result = productService.update(productDTO);
		
		String msg = "수정 실패";
		String path = "./list";
		if (result > 0) {
			msg = "수정 성공";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("path", path);
		
		return "commons/result";
	}
	
	
	// 댓글
	@GetMapping("commentList")
	public void commentList(ProductCommentDTO productCommentDTO, Pager pager, Model model) throws Exception {
		List<ProductCommentDTO> list = productService.commentList(productCommentDTO, pager);
		model.addAttribute("list", list);
	}
	
	@PostMapping("commentAdd")
	@ResponseBody
	public int commentAdd(ProductCommentDTO productCommentDTO, HttpSession session) throws Exception {
		UserDTO userDTO = (UserDTO) session.getAttribute("user");
		productCommentDTO.setUsername(userDTO.getUsername());
		
		int result = productService.commentAdd(productCommentDTO);
		
		return result;
	}
	
	@PostMapping("commentDelete")
	@ResponseBody
	public int commentDelete(ProductCommentDTO productCommentDTO) throws Exception {
		int result = productService.commentDelete(productCommentDTO);
		return result;
	}
	
	@GetMapping("addCart")
	@ResponseBody
	public int addCart(ProductDTO productDTO, @AuthenticationPrincipal UserDetails userDetails) throws Exception {
		int result = productService.addCart(productDTO, userDetails);
		
		return result;
	}
	
	@GetMapping("cart") 
	public void cart(Model model, @AuthenticationPrincipal UserDetails userDetails) throws Exception {		
		List<ProductDTO> productList = productService.cartList(userDetails);
		model.addAttribute("productList", productList);
	}
	
}
