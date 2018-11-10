package com.ecommerce.repository;

import com.ecommerce.model.User;

public interface IUserDAO {

	public void register(User user);
	
	public User login(User user);
	
	public User getUserDTO(int userId);
	
	public Boolean changeUserInfo(User user);
}
