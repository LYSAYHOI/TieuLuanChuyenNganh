package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.DTO.CategoryDTO;
import com.ecommerce.model.Category;
import com.ecommerce.repositoryImpl.CategoryDAO;
import com.ecommerce.service.CategoryService;
import com.ecommerce.utilities.UtilityConvertBetweenEntityAndDTO;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDAO ;//= new CategoryDAO();
	
	@Transactional
	@Override
	public List<CategoryDTO> getCategoryList() {
		List<Category> listCategory = categoryDAO.getCategoryList();
		List<CategoryDTO> listCategoryDTO = new ArrayList<>();
		for(Category x : listCategory) {
			listCategoryDTO.add(UtilityConvertBetweenEntityAndDTO.convertToCategoryDTO(x));
		}
		return listCategoryDTO;
	}
}
