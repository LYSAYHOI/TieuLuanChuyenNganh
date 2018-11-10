package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.model.SubCategory;

public interface ISubCategoryDAO {
	
	public List<SubCategory> getSubCategoryList();
	
	public SubCategory getSubCategor(int id);
}
