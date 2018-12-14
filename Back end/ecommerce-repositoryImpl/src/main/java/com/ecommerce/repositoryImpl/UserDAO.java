package com.ecommerce.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> query = builder.createQuery(User.class);
		Root<User> root = query.from(User.class);
		query.select(root);
		query.where(builder.and(
				builder.equal(root.get("loginName"), user.getLoginName()),
				builder.equal(root.get("password"), user.getPassword())));
		List<User> userList = entityManager.createQuery(query).getResultList();
		if(userList.isEmpty())
			return null;
		return userList.get(0);
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
