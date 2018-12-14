package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.DTO.OrderAndCartDTO;
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
}
