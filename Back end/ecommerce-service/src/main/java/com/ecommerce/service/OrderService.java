package com.ecommerce.service;

import java.util.List;

import com.ecommerce.DTO.OrderAndCartDTO;
import com.ecommerce.DTO.OrderDetailDTO;
import com.ecommerce.DTO.OrderProductDTO;

public interface OrderService {
	public boolean createOrderProduct(OrderAndCartDTO orderAndCartDTO);
	public List<OrderProductDTO> getOrderByUser(int userId);
	public List<OrderProductDTO> cancelOrder(OrderProductDTO orderDTO);
	public List<OrderDetailDTO> getOrderDetail(int orderId);
	public List<OrderDetailDTO> getOrderDetailByProducer(int idProducer);
	public boolean inprocessOrderDetail(OrderDetailDTO orderDetailDTO);
	//public List<OrderDetailDTO>	transferingOrderDetail(OrderDetailDTO orderDetailDTO);
	public boolean failOrderDetail(OrderDetailDTO orderDetailDTO);
	public List<OrderDetailDTO> getInprocessOrderDetailByProducer(int idProducer);
	public List<OrderDetailDTO> getFailOrderDetailByProducer(int idProducer);
}
