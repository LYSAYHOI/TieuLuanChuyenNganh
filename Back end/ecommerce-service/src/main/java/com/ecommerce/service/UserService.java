package com.ecommerce.service;

import java.util.List;

import com.ecommerce.DTO.UserDTO;

public interface UserService {
	public void register(UserDTO userDTO);
	public List<Object> login(UserDTO userDTO);
	public boolean logout(String token);
	public UserDTO getUserById(int userId);
	public Boolean changeUserInfo(UserDTO userDTO);
}
