package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.DTO.ResponseDTO;
import com.ecommerce.serviceImpl.CategoryServiceImpl;
import com.ecommerce.serviceImpl.ProductServiceImpl;

@RestController
public class MainPageController {
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@GetMapping("GetProductBySearch")
	@CrossOrigin
	public ResponseDTO getProductBySearch(@RequestParam String keyWord, @RequestParam int index, @RequestParam int maxResult) {
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Product search");
		res.setObject(productService.getProductBySearch(keyWord, index, maxResult));
		return res;
	}
	
/*	
 * 
	@GetMapping("GetCategoryList")
	@CrossOrigin
	public List<CategoryDTO> getCategory(){
		return categoryService.getCategoryList();
	}*/
	
	@GetMapping("GetCategoryList")
	@CrossOrigin
	public ResponseDTO getCategory(){
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("My Message");
		res.setObject(categoryService.getCategoryList());
		return res;
	}
	
	@GetMapping("GetProductList")
	@CrossOrigin
	public ResponseDTO getproduct(@RequestParam int index, @RequestParam int maxResult){
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Product List");
		res.setObject(productService.getProductList(index, maxResult));
		return res;
	}
	
	@GetMapping("GetProductCount")
	@CrossOrigin
	public ResponseDTO getproductCount(){
		ResponseDTO res = new ResponseDTO();
		res.setStatus(0);
		res.setMessage("Amount of products");
		res.setObject(productService.getProductCount());
		return res;
	}
}
