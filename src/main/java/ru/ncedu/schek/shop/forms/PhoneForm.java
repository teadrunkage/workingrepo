package ru.ncedu.schek.shop.forms;


import org.springframework.web.multipart.MultipartFile;

public class PhoneForm {
	private String model_name;
	private String color_name;
	private Long price;
	private String comment;
	
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	private MultipartFile picture;
	private long phoneId;
	
	public String getModel_name() {
		return model_name;
	}
	public void setModel_name(String model_name) {
		this.model_name = model_name;
	}
	public String getColor_name() {
		return color_name;
	}
	public void setColor_name(String color_name) {
		this.color_name = color_name;
	}
	public long getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(long phoneId) {
		this.phoneId = phoneId;
	}
	public MultipartFile getPicture() {
		return picture;
	}
	public void setPicture(MultipartFile picture) {
		this.picture = picture;
	}

}
