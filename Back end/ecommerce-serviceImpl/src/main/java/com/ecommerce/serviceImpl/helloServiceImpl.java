package com.ecommerce.serviceImpl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.repositoryImpl.helloImpl;
import com.ecommerce.service.IhelloService;

@Service
public class helloServiceImpl implements IhelloService{
	
	@Autowired
	private helloImpl helloRepo;
	
	@Override
	public String getData() {
		return helloRepo.getData();
	}
	
	@Transactional
	@Override
	public int insertData() {
		return helloRepo.insertData();
	}
	
	@Transactional
	@Override
	public int removeData() {
		return helloRepo.removeData();
	}
	
	@Transactional
	@Override
	public int updateData() {
		return helloRepo.updateData();
	}

}
