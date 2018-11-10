package com.ecommerce.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.hello;
import com.ecommerce.repository.Ihello;

@Repository
public class helloImpl implements Ihello{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public String getData() {
		List<hello> listData = entityManager.createQuery("from hello").getResultList();
		return String.valueOf(listData.get(0).getId());
	}

	@Override
	public int insertData() {
		hello he = new hello();
		he.setMessage("hi there");
		try {
			entityManager.persist(he);
			return 1;
		}catch(Exception ex) {
			System.out.println("get error: "+ ex);
			return 0;
		}
	}

	@Override
	public int removeData() {
		try{
			hello removed = entityManager.find(hello.class, 2);
			entityManager.remove(removed);
			return 1;
		}catch(Exception ex) {
			System.out.println("error: "+ ex);
			return 0;
		}
		
	}

	@Override
	public int updateData() {
		try{
			hello updating = entityManager.find(hello.class, 1);
			updating.setMessage("hello Hoi-sama");
			entityManager.merge(updating);
			return 1;
		}catch(Exception ex) {
			System.out.println("error: "+ ex);
			return 0;
		}
	}
}
