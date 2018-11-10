package com.ecommerce.repositoryImpl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.User;
import com.ecommerce.repository.IUserDAO;

@Repository
public class UserDAO implements IUserDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void register(User user) {
		entityManager.persist(user);
	}

	@Override
	public User login(User user) {
		/*User loginUser = (User)entityManager.createCriteria(User.class)
			.add(Restrictions.like("loginName", user.getLoginName()))
			.add(Restrictions.like("password", user.getPassword()))
			.uniqueResult();
		return loginUser;
		*/
		return null;
	}

	@Override
	public User getUserDTO(int userId) {
		/*
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.createCriteria(User.class)
				.add(Restrictions.eq("userId", userId)).uniqueResult();
		session.flush();
		session.clear();
		return user;
		*/
		return null;
	}

	@Override
	public Boolean changeUserInfo(User user) {
		try {
			entityManager.merge(user);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}
}
