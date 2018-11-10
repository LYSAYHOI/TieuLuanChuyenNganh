package com.ecommerce.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.SubCategory;
import com.ecommerce.repository.ISubCategoryDAO;

@Repository
public class SubCategoryDAO implements ISubCategoryDAO {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<SubCategory> getSubCategoryList(){
		List<SubCategory> subCategory = entityManager
				.createQuery("from SubCategory order by category.idCategory").getResultList();
		return subCategory;
	}

	@Override
	public SubCategory getSubCategor(int id){
		/*
		 * SubCategory subCategory = (SubCategory)entityManager.createCriteria(SubCategory.class)
				.add(Restrictions.eq("idSubCategory", id)).uniqueResult();
		return subCategory; 
		*/
		return null;
	}
}
