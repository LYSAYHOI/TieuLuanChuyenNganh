package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.model.Product;

public interface IProductDAO {
	
	/*public List<Product> getProducList(){
		Session session = sessionFactory.getCurrentSession();
		Transaction transaction = session.beginTransaction();
		List<Product> product = session.createQuery("from Product").list();
		transaction.commit();
		session.close();
		return product;
	}*/
	
	public Product getProduct(int idProduct);
	
	public Long getProductCount();
	
	public List<Product> getProducList(int index, int maxResult);
	
	public List<Product> getTheMostViewedProduct(int index, int maxResult);
	
	public List<Product> getNewProduct(int index, int maxResult);
	
	public List<Product> getProductBySubCategory(int categoryId, int index, int maxResult);
	
	public Long getProductBySubCategoryCount(int categoryId);
	
	public List<Product> getProductBySearch(String keyWord, int index, int maxResult);
	
	public Long getProductBySearchCount(String keyWord);
	
	public List<Product> getProductByCategory(int categoryId, int index, int maxResult);
	
	public Long getProductByCategoryCount(int categoryId);
	
	//create, change info product
	public boolean addProduct(Product product);
	
	public List<Product> getProductByUser(int userID);
	
	public boolean editProduct(Product product);
}
