package com.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.DTO.OrderAndCartDTO;
import com.ecommerce.DTO.OrderDetailDTO;
import com.ecommerce.DTO.OrderProductDTO;
import com.ecommerce.DTO.ResponseDTO;
import com.ecommerce.serviceImpl.AuthenticationService;
import com.ecommerce.serviceImpl.OrderServiceImpl;

@RestController
@RequestMapping("Order")
public class OrderController {

	@Autowired
	private OrderServiceImpl orderService;
	
	/*@Autowired
	private AuthenticationService authenticationService;
	*/
	@PostMapping("CreateOrder")
	@CrossOrigin
	public ResponseDTO createOrder(@RequestBody OrderAndCartDTO orderAndCartDTO, @RequestHeader("token") String token) {
		/*if(!authenticationService.checkIsConsumer(token)) {
			ResponseDTO error = new ResponseDTO();
			error.setMessage("Create order fail");
			error.setStatus(0);
			return error;
		}*/
		if(orderService.createOrderProduct(orderAndCartDTO)) {
			ResponseDTO res = new ResponseDTO();
			res.setMessage("Create order success");
			res.setStatus(0);
			return res;
		}
		ResponseDTO error = new ResponseDTO();
		error.setMessage("Create order fail");
		error.setStatus(0);
		return error;
	}
	
	@GetMapping("GetOrder")
	@CrossOrigin
	public ResponseDTO getOrder(@RequestParam int userId) {
		try {
			List<OrderProductDTO> listOrder = orderService.getOrderByUser(userId);
			ResponseDTO res = new ResponseDTO();
			res.setMessage("Successfully get order list");
			res.setStatus(0);
			res.setObject(listOrder);
			return res;
		}catch(Exception ex) {
			ex.printStackTrace();
			ResponseDTO error = new ResponseDTO();
			error.setMessage("failed get order list");
			error.setStatus(1);
			return error;
		}
	}
	
	@GetMapping("GetOrderDetail")
	@CrossOrigin
	public ResponseDTO getOrderDetail(@RequestParam int orderId) {
		List<OrderDetailDTO> listOrderDetail = orderService.getOrderDetail(orderId);
		
		if(listOrderDetail!= null && listOrderDetail.size() > 0) {
			ResponseDTO res = new ResponseDTO();
			res.setMessage("Successfully get order detail list");
			res.setStatus(0);
			res.setObject(listOrderDetail);
			return res;
		}
		ResponseDTO error = new ResponseDTO();
		error.setMessage("failed get order detail list");
		error.setStatus(1);
		error.setObject(listOrderDetail);
		return error;
	}
	
	@PostMapping("CancelOrder")
	@CrossOrigin
	public ResponseDTO cancelOrder(@RequestBody OrderProductDTO orderDTO) {
		List<OrderProductDTO> listOrder = orderService.cancelOrder(orderDTO);
		if(listOrder != null) {
			ResponseDTO res = new ResponseDTO();
			res.setMessage("Cancel order success");
			res.setStatus(0);
			res.setObject(listOrder);
			return res;
		}
		ResponseDTO error = new ResponseDTO();
		error.setMessage("fail to canel order");
		error.setStatus(1);
		return error;
	}
	
}
