package com.ecommerce.repositoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.OrderStatus;
import com.ecommerce.repository.IOrderDAO;

@Repository
public class OrderDAO implements IOrderDAO{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public int CreateOrderProduct(OrderProduct order) {
		try {
			entityManager.persist(order);
			entityManager.flush();
			return order.getIdOrder();
		}catch(Exception ex) {
			ex.printStackTrace();
			return 0;
		}
	}
	
	@Override
	public boolean addOrderDetail(OrderDetail orderDetail) {
		try {
			entityManager.persist(orderDetail);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public List<OrderProduct> getOrderByUser(int userId) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderProduct> query = builder.createQuery(OrderProduct.class);
		Root<OrderProduct> root = query.from(OrderProduct.class);
		query.select(root);
		query.where(builder.equal(root.get("consumer").get("userId"), userId));
		
		List<OrderProduct> listOrder = entityManager.createQuery(query).getResultList();
		return listOrder;
	}

	@Override
	public OrderProduct getOrderById(int id) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<OrderProduct> query = builder.createQuery(OrderProduct.class);
		Root<OrderProduct> root = query.from(OrderProduct.class);
		query.select(root);
		query.where(builder.equal(root.get("idOrder"), id));
		
		return entityManager.createQuery(query).getSingleResult();
	}

	@Override
	public boolean changeOrderStatus(OrderProduct order) {
		try {
			entityManager.merge(order);
			return true;
		}catch(Exception ex) {
			return false;
		}
	}

	@Override
	public boolean changeOrderDetailStatus(OrderDetail orderDetail) {
		try {
			entityManager.merge(orderDetail);
			return true;
		}catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	@Override
	public List<OrderDetail> getOrderDetail(int orderId){
		try {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OrderDetail> query = builder.createQuery(OrderDetail.class);
			Root<OrderDetail> OrderDetailRoot = query.from(OrderDetail.class);
			query.select(OrderDetailRoot);
			query.where(builder.equal(OrderDetailRoot.get("order").get("idOrder"), orderId));
			List<OrderDetail> listOrderDetail =  entityManager.createQuery(query).getResultList();

			entityManager.flush();
			entityManager.clear();
			return listOrderDetail;
		}catch(Exception ex) {
			return null;
		}
	}

	@Override
	public List<OrderDetail> getOrderDetailByProducer(int orderId, int producerId){
		try {
			return entityManager.createQuery("from OrderDetail where order.idOrder = "+ orderId
					+ " and product.user.userId = "+producerId).getResultList();
		}catch(Exception ex) {
			return null;
		}
	}

	@Override
	public List<OrderDetail> getWaitingOrderDetailByProducer(int idProducer){
		try {
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OrderDetail> query = builder.createQuery(OrderDetail.class);
			Root<OrderDetail> OrderDetailRoot = query.from(OrderDetail.class);
			query.select(OrderDetailRoot);
			query.where(builder.and(
				builder.equal(OrderDetailRoot.get("product").get("user").get("userId"), idProducer),
				builder.equal(OrderDetailRoot.get("status"), OrderStatus.NONE)
			));
			query.orderBy(builder.asc(OrderDetailRoot.get("order").get("idOrder")));
			
			return entityManager.createQuery(query).getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<OrderDetail> getInprocessOrderDetailByProducer(int idProducer){
		try {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OrderDetail> query = builder.createQuery(OrderDetail.class);
			Root<OrderDetail> OrderDetailRoot = query.from(OrderDetail.class);
			query.select(OrderDetailRoot);
			query.where(builder.and(
				builder.equal(OrderDetailRoot.get("product").get("user").get("userId"), idProducer),
				builder.equal(OrderDetailRoot.get("status"), OrderStatus.INPROCESS)
			));
			query.orderBy(builder.asc(OrderDetailRoot.get("order").get("idOrder")));
			
			return entityManager.createQuery(query).getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public List<OrderDetail> getFailOrderDetailByProducer(int idProducer){
		try {
			
			CriteriaBuilder builder = entityManager.getCriteriaBuilder();
			CriteriaQuery<OrderDetail> query = builder.createQuery(OrderDetail.class);
			Root<OrderDetail> OrderDetailRoot = query.from(OrderDetail.class);
			query.select(OrderDetailRoot);
			query.where(builder.and(
				builder.equal(OrderDetailRoot.get("product").get("user").get("userId"), idProducer),
				builder.equal(OrderDetailRoot.get("status"), OrderStatus.FAIL)
			));
			query.orderBy(builder.asc(OrderDetailRoot.get("order").get("idOrder")));
			
			return entityManager.createQuery(query).getResultList();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
