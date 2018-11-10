package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.service.SubCategoryService;
import com.ecommerce.DTO.SubCategoryDTO;
import com.ecommerce.model.SubCategory;
import com.ecommerce.repositoryImpl.SubCategoryDAO;
import com.ecommerce.utilities.UtilityConvertBetweenEntityAndDTO;

@Service
public class SubCategoryServiceImpl implements SubCategoryService{
	
	@Autowired
	private SubCategoryDAO subCategoryDAO; // = new SubCategoryDAO();

	@Transactional
	@Override
	public List<SubCategoryDTO> getSubCategoryList() {
		List<SubCategory> listSubCategory = subCategoryDAO.getSubCategoryList();
		List<SubCategoryDTO> listSubCategoryDTO = new ArrayList<>();
		for(SubCategory x : listSubCategory) {
			listSubCategoryDTO.add(UtilityConvertBetweenEntityAndDTO.convertToSubCategoryDTO(x));
		}
		return listSubCategoryDTO;
	}
	 
}
