package com.ecommerce.utilities;

import com.ecommerce.DTO.CategoryDTO;
import com.ecommerce.DTO.OrderDetailDTO;
import com.ecommerce.DTO.OrderProductDTO;
import com.ecommerce.DTO.OrderStatusDTO;
import com.ecommerce.DTO.ProductDTO;
import com.ecommerce.DTO.RoleDTO;
import com.ecommerce.DTO.SubCategoryDTO;
import com.ecommerce.DTO.UserDTO;
import com.ecommerce.model.Category;
import com.ecommerce.model.OrderDetail;
import com.ecommerce.model.OrderProduct;
import com.ecommerce.model.OrderStatus;
import com.ecommerce.model.Product;
import com.ecommerce.model.Role;
import com.ecommerce.model.SubCategory;
import com.ecommerce.model.User;

public class UtilityConvertBetweenEntityAndDTO {
public UtilityConvertBetweenEntityAndDTO() {}
	
	//Convert category
	public static CategoryDTO convertToCategoryDTO(Category category) {
		CategoryDTO categoryDTO = new CategoryDTO();
		categoryDTO.setCategoryName(category.getCategoryName());
		categoryDTO.setIdCategory(category.getIdCategory());
		return categoryDTO;
	}
	
	public static Category convertToCategoryEntity(CategoryDTO categoryDTO) {
		Category category = new Category();
		category.setCategoryName(categoryDTO.getCategoryName());
		category.setIdCategory(categoryDTO.getIdCategory());
		return category;
	}
	
	//convert subcategory
	public static SubCategoryDTO convertToSubCategoryDTO(SubCategory subCategory) {
		SubCategoryDTO subCategoryDTO = new SubCategoryDTO();
		subCategoryDTO.setSubCategoryName(subCategory.getSubCategoryName());
		subCategoryDTO.setIdSubCategory(subCategory.getIdSubCategory());
		subCategoryDTO.setCategoryDTO(convertToCategoryDTO(subCategory.getCategory()));
		return subCategoryDTO;
	}
	
	public static SubCategory convertToSubCategoryEntity(SubCategoryDTO subCategoryDTO) {
		SubCategory subCategory = new SubCategory();
		subCategory.setSubCategoryName(subCategoryDTO.getSubCategoryName());
		subCategory.setIdSubCategory(subCategoryDTO.getIdSubCategory());
		subCategory.setCategory(convertToCategoryEntity(subCategoryDTO.getCategoryDTO()));
		return subCategory;
	}
	
	//convert product
	public static ProductDTO convertToProductDTO(Product product) {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setIdProduct(product.getIdProduct());
		productDTO.setProductName(product.getProductName());
		productDTO.setAddDate(product.getAddDate());
		productDTO.setDescription(product.getDescription());
		productDTO.setImage(product.getImage());
		productDTO.setPrice(product.getPrice());
		productDTO.setStatus(product.isStatus());
		productDTO.setSubCategoryDTO(convertToSubCategoryDTO(product.getSubCategory()));
		productDTO.setViewCounter(product.getViewCounter());
		if(product.getUser()!= null)
			productDTO.setUserDTO(convertToUserDTO(product.getUser()));
		return productDTO;
	}
	
	public static Product convertToProductEntity(ProductDTO productDTO) {
		Product product = new Product();
		product.setIdProduct(productDTO.getIdProduct());
		product.setProductName(productDTO.getProductName());
		product.setAddDate(productDTO.getAddDate());
		product.setDescription(productDTO.getDescription());
		product.setImage(productDTO.getImage());
		product.setPrice(productDTO.getPrice());
		product.setStatus(productDTO.isStatus());
		if(productDTO.getSubCategoryDTO() != null)
			product.setSubCategory(convertToSubCategoryEntity(productDTO.getSubCategoryDTO()));
		product.setViewCounter(productDTO.getViewCounter());
		if(productDTO.getUserDTO()!= null)
			product.setUser(convertToUserEntity(productDTO.getUserDTO()));
		return product;
	}
	
	//convert user
	public static UserDTO convertToUserDTO(User user) {
		UserDTO userDTO = new UserDTO();
		userDTO.setIdentityNumber(user.getIdentityNumber());
		userDTO.setLoginName(user.getLoginName());
		userDTO.setPassword(user.getPassword());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		userDTO.setStatus(user.isStatus());
		userDTO.setUserId(user.getUserId());
		userDTO.setUserName(user.getUserName());
		userDTO.setEmail(user.getEmail());
		userDTO.setRole(RoleDTO.valueOf(user.getRole().name()));
		return userDTO;
	}
	
	public static User convertToUserEntity(UserDTO userDTO) {
		User user = new User();
		user.setIdentityNumber(userDTO.getIdentityNumber());
		user.setLoginName(userDTO.getLoginName());
		user.setPassword(userDTO.getPassword());
		user.setPhoneNumber(userDTO.getPhoneNumber());
		user.setStatus(userDTO.isStatus());
		user.setUserId(userDTO.getUserId());
		user.setUserName(userDTO.getUserName());
		user.setEmail(userDTO.getEmail());
		if(userDTO.getRole() != null)
			user.setRole(Role.valueOf(userDTO.getRole().name()));
		return user;
	}
	//convert order
	public static OrderProductDTO convertToOrderDTO(OrderProduct order) {
		OrderProductDTO orderDTO = new OrderProductDTO();
		orderDTO.setIdOrder(order.getIdOrder());
		orderDTO.setOrderDate(order.getOrderDate());
		orderDTO.setContent(order.getContent());
		orderDTO.setPrice(order.getPrice());
		orderDTO.setStatusDTO(OrderStatusDTO.valueOf(order.getStatus().name()));
		orderDTO.setConsumerDTO(convertToUserDTO(order.getConsumer()));
		orderDTO.setAddress(order.getAddress());
		orderDTO.setFullName(order.getFullName());
		orderDTO.setPhoneNumber(order.getPhoneNumber());
		return orderDTO;
	}
	
	public static OrderProduct convertToOrderEntity(OrderProductDTO orderDTO) {
		OrderProduct order = new OrderProduct();
		order.setIdOrder(orderDTO.getIdOrder());
		order.setOrderDate(orderDTO.getOrderDate());
		order.setContent(orderDTO.getContent());
		order.setPrice(orderDTO.getPrice());
		order.setStatus(OrderStatus.valueOf(orderDTO.getStatusDTO().name()));
		if(orderDTO.getConsumerDTO() != null)
			order.setConsumer(convertToUserEntity(orderDTO.getConsumerDTO()));
		order.setAddress(orderDTO.getAddress());
		order.setFullName(orderDTO.getFullName());
		order.setPhoneNumber(orderDTO.getPhoneNumber());
		return order;
	}
	
	//convert order detail
	public static OrderDetailDTO convertToOrderDetailDTO(OrderDetail orderDetail) {
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setQuantity(orderDetail.getQuantity());
		orderDetailDTO.setOrder(convertToOrderDTO(orderDetail.getOrder()));
		orderDetailDTO.setProduct(convertToProductDTO(orderDetail.getProduct()));
		orderDetailDTO.setStatusDTO(OrderStatusDTO.valueOf(orderDetail.getStatus().name()));
		return orderDetailDTO;
	}
	
	public static OrderDetail convertToOrderDetailEntity(OrderDetailDTO orderDetailDTO) {
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setQuantity(orderDetailDTO.getQuantity());
		orderDetail.setOrder(convertToOrderEntity(orderDetailDTO.getOrder()));
		orderDetail.setProduct(convertToProductEntity(orderDetailDTO.getProduct()));
		orderDetail.setStatus(OrderStatus.valueOf(orderDetailDTO.getStatusDTO().name()));
		return orderDetail;
	}
}
