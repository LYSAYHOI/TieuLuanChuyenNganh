package com.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.serviceImpl.helloServiceImpl;

@RestController
public class helloclass {

	@Autowired
	helloServiceImpl helloservice;
	
	@GetMapping("/")
	public String helloworld() {
		return "hello world";
	}
	
	@GetMapping("/message")
	public String getData() {
		return helloservice.getData();
	}
	
	@GetMapping("/savemessage")
	public String insertData() {
		if(helloservice.insertData() == 1) 
			return "success";
		return "error";
	}
	
	@GetMapping("/removemessage")
	public String removeData() {
		if(helloservice.removeData() == 1) 
			return "success";
		return "error";
	}
	
	@GetMapping("/updatemessage")
	public String updateData() {
		if(helloservice.updateData() == 1) 
			return "success";
		return "error";
	}
}
