package com.ecommerce.repository;

import java.util.List;

import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.OrderProduct;

public interface IOrderDAO {
	
	public int CreateOrderProduct(OrderProduct order);
	
	public boolean addOrderDetail(OrderDetail orderDetail);
	
	public List<OrderProduct> getOrderByUser(int userId);
	
	public OrderProduct getOrderById(int id);
	
	public boolean changeOrderStatus(OrderProduct order);
	
	public boolean changeOrderDetailStatus(OrderDetail orderDetail);
	
	public List<OrderDetail> getOrderDetail(int orderId);
	
	public List<OrderDetail> getOrderDetailByProducer(int orderId, int producerId);
	
	public List<OrderDetail> getWaitingOrderDetailByProducer(int idProducer);
	
	public List<OrderDetail> getInprocessOrderDetailByProducer(int idProducer);
	
	public List<OrderDetail> getFailOrderDetailByProducer(int idProducer);
}
