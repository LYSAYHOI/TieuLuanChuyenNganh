package com.ecommerce.DTO;

import java.util.Date;

public class ProductDTO {
private int idProduct;
	
	private String productName;
	private String description;
	private double price;
	private String image;
	private boolean status;
	private Date addDate = new Date();
	private int viewCounter = 0;
	private CategoryDTO CategoryDTO;
	private UserDTO userDTO;
	private ManufacturerDTO manufacturerDTO;

	public ProductDTO() {}

	public int getIdProduct() {
		return idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public CategoryDTO getCategoryDTO() {
		return CategoryDTO;
	}

	public void setCategoryDTO(CategoryDTO categoryDTO) {
		CategoryDTO = categoryDTO;
	}

	public ManufacturerDTO getManufacturerDTO() {
		return manufacturerDTO;
	}

	public void setManufacturerDTO(ManufacturerDTO manufacturerDTO) {
		this.manufacturerDTO = manufacturerDTO;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getViewCounter() {
		return viewCounter;
	}

	public void setViewCounter(int viewCounter) {
		this.viewCounter = viewCounter;
	}

	public Date getAddDate() {
		return addDate;
	}

	public void setAddDate(Date addDate) {
		this.addDate = addDate;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
}
