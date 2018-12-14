package com.ecommerce.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.service.UserService;
import com.ecommerce.DTO.UserDTO;
import com.ecommerce.model.User;
import com.ecommerce.repositoryImpl.UserDAO;
import com.ecommerce.utilities.UtilityConvertBetweenEntityAndDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Transactional
	@Override
	public void register(UserDTO userDTO) {
		userDTO.setPassword(DigestUtils.sha1Hex(userDTO.getPassword()));
		userDAO.register(UtilityConvertBetweenEntityAndDTO.convertToUserEntity(userDTO));
	}
	
	@Transactional
	@Override
	public List<Object> login(UserDTO userDTO) {
		if(userDTO.getLoginName() == null || userDTO.getPassword() == null)
			return null;
		userDTO.setPassword(DigestUtils.sha1Hex(userDTO.getPassword()));
		User loginUser = userDAO.login(UtilityConvertBetweenEntityAndDTO.convertToUserEntity(userDTO));
		if(loginUser != null) {
			List<Object> list = new ArrayList<>();
			UserDTO loginUserDTO = UtilityConvertBetweenEntityAndDTO.convertToUserDTO(loginUser);
			String token = AuthenticationService.getInstance().initToken(loginUserDTO);
			loginUserDTO.setPassword("");
			list.add(loginUserDTO);
			list.add(token);
			return list;
		}
		return null;
	}
	@Override
	public boolean logout(String token) {
		if(AuthenticationService.getInstance().releaseToken(token) != null)
			return true;
		return false;
	}
	
	@Transactional
	@Override
	public UserDTO getUserById(int userId) {
		return UtilityConvertBetweenEntityAndDTO.convertToUserDTO(userDAO.getUserDTO(userId));
	}
	
	@Transactional
	@Override
	public Boolean changeUserInfo(UserDTO userDTO) {
		UserDTO userInfoChange = getUserById(userDTO.getUserId());
		userDTO.setLoginName(userInfoChange.getLoginName());
		userDTO.setPassword(userInfoChange.getPassword());
		userDTO.setRole(userInfoChange.getRole());
		return userDAO.changeUserInfo(UtilityConvertBetweenEntityAndDTO.convertToUserEntity(userDTO));
	}
}
