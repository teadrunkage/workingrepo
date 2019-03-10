package ru.ncedu.schek.shop.restentities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PictureForRest {

    private Long id;

    private String name;

    private String color;

    private PhoneForRest phone;

    private ModelForRest model;

    private byte[] bytes;

    public PictureForRest(PhoneForRest phone, String color, String name, byte[] bytes) {
        this.color = color;
        this.phone = phone;
        this.name = name;
        this.bytes = bytes;
    }

    public PictureForRest(ModelForRest model, String color, String name, byte[] bytes) {
        this.color = color;
        this.model = model;
        this.name = name;
        this.bytes = bytes;
    }

    public PictureForRest() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public PhoneForRest getPhone() {
		return phone;
	}

	public void setPhone(PhoneForRest phone) {
		this.phone = phone;
	}

	public ModelForRest getModel() {
		return model;
	}

	public void setModel(ModelForRest model) {
		this.model = model;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}
    
    
}
