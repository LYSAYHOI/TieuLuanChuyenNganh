package com.ecommerce.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.Product;
import com.ecommerce.model.User;
import com.ecommerce.repository.IProductDAO;

@Repository
public class ProductDAO implements IProductDAO{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Product getProduct(int idProduct) {
		Product product = (Product)entityManager.find(Product.class, idProduct);
		return product;
	}

	@Override
	public Long getProductCount() {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Product> root = query.from(Product.class);
		query.select(builder.count(root));
		query.where(builder.equal(root.get("status"), true));
		return entityManager.createQuery(query).getSingleResult();
	
	}

	@Override
	public List<Product> getProducList(int index, int maxResult) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root);
		query.where(builder.equal(root.get("status"), true));
		List<Product> product = entityManager.createQuery(query)
				.setFirstResult((index-1)*maxResult)
				.setMaxResults(maxResult)
				.getResultList();
		
		return product;
		
	}

	@Override
	public List<Product> getTheMostViewedProduct(int index, int maxResult){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root);
		query.orderBy(builder.desc(root.get("viewCounter")));
		query.where(builder.equal(root.get("status"), true));
		List<Product> product = entityManager.createQuery(query)
				.setFirstResult((index-1)*maxResult)
				.setMaxResults(maxResult)
				.getResultList();
		return product;
	}

	@Override
	public List<Product> getNewProduct(int index, int maxResult){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root);
		query.orderBy(builder.desc(root.get("addDate")));
		query.where(builder.equal(root.get("status"), true));
		List<Product> product = entityManager.createQuery(query)
				.setFirstResult((index-1)*maxResult)
				.setMaxResults(maxResult)
				.getResultList();
		return product;
	}

	@Override
	public List<Product> getProductBySubCategory(int categoryId, int index, int maxResult){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root);
		query.where(builder.and(
				builder.equal(root.get("status"), true), 
				builder.equal(root.get("subCategory").get("idSubCategory"), categoryId)));
		List<Product> product = entityManager.createQuery(query)
				.setFirstResult((index-1)*maxResult)
				.setMaxResults(maxResult)
				.getResultList();
		return product;
	}

	@Override
	public Long getProductBySubCategoryCount(int categoryId) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Product> root = query.from(Product.class);
		query.select(builder.count(root));
		query.where(builder.and(
				builder.equal(root.get("status"), true), 
				builder.equal(root.get("subCategory").get("idSubCategory"), categoryId)));
		return entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public List<Product> getProductBySearch(String keyWord, int index, int maxResult){
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root);
		query.where(builder.and(
				builder.equal(root.get("status"), true), 
				builder.like(root.get("productName"), "%"+keyWord+"%")));
		List<Product> product = entityManager.createQuery(query)
				.setFirstResult((index-1)*maxResult)
				.setMaxResults(maxResult)
				.getResultList();
		return product;
	}

	@Override
	public Long getProductBySearchCount(String keyWord) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Product> root = query.from(Product.class);
		query.select(builder.count(root));
		query.where(builder.and(
				builder.equal(root.get("status"), true), 
				builder.like(root.get("productName"), "%"+keyWord+"%")));
		return entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public List<Product> getProductByCategory(int categoryId, int index, int maxResult){
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Product> query = builder.createQuery(Product.class);
		Root<Product> root = query.from(Product.class);
		query.select(root);
		query.where(builder.and(
				builder.equal(root.get("status"), true), 
				builder.equal(root.get("category").get("idCategory"), categoryId)));
		List<Product> product = entityManager.createQuery(query)
				.setFirstResult((index-1)*maxResult)
				.setMaxResults(maxResult)
				.getResultList();
		return product;
	}

	@Override
	public Long getProductByCategoryCount(int categoryId) {
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = builder.createQuery(Long.class);
		Root<Product> root = query.from(Product.class);
		query.select(builder.count(root));
		query.where(builder.and(
				builder.equal(root.get("status"), true), 
				builder.equal(root.get("category").get("idCategory"), categoryId)));
		return entityManager.createQuery(query).getSingleResult();
	}
	
	//create, change info product
	@Override
	public boolean addProduct(Product product) {
		try {
			entityManager.persist(product);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
	
	@Override
	public List<Product> getProductByUser(int userID){
//		Session session = sessionFactory.getCurrentSession();
//		/*User user = (User)session.createCriteria(User.class)
//				.add(Restrictions.eq("userId", userID)).uniqueResult();
//		if(user == null) return null;*/
//		List<Product> listProduct = session.createCriteria(Product.class)
//				.add(Restrictions.eq("user.userId" ,userID))
//				.list();
//		return listProduct;
		
		return null;
	}

	@Override
	public boolean editProduct(Product product) {
		try {
			entityManager.merge(product);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}
}
