package com.ecommerce.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.Category;
import com.ecommerce.repository.ICategoryDAO;

@Repository
public class CategoryDAO implements ICategoryDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Category> getCategoryList() {
		
		List<Category> category = entityManager.createQuery("from Category").getResultList();
		return category;
	}

}
