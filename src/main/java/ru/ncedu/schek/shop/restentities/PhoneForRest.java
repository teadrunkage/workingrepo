package ru.ncedu.schek.shop.restentities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.ncedu.schek.shop.entities.Phone;

import java.util.List;

import javax.persistence.Entity;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneForRest {
    private Long phoneId;

    private ModelForRest model;

    private List<PictureForRest> pictures;

    private long price;

    private String color;
    
    private String link;

    public PhoneForRest( ModelForRest model, long price, String color) {
        this.model = model;
        this.price = price;
        this.color = color;
    }
    
    public PhoneForRest(ModelForRest model, String color) {
        this.model = model;
        this.color = color;
    }
    
    public PhoneForRest (){
    }

    public PhoneForRest(Phone p) {
    	this.link = "http://localhost:8080/guestphonepage?phoneId="+p.getId();
    //	this.phoneId = p.getId();
    	this.color = p.getColor();
    	this.price = p.getPrice();
	}
	@Override
    public String toString() {
        return "Phone{" +
                "color='" + color + '\'' +
                ", price=" + price +
                ", model=" + model +
                ", phoneId=" + phoneId +
                '}';
    }
	public Long getPhoneId() {
		return phoneId;
	}
	public void setPhoneId(Long phoneId) {
		this.phoneId = phoneId;
	}
	public ModelForRest getModel() {
		return model;
	}
	public void setModel(ModelForRest model) {
		this.model = model;
	}
	public List<PictureForRest> getPictures() {
		return pictures;
	}
	public void setPictures(List<PictureForRest> pictures) {
		this.pictures = pictures;
	}
	public long getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
    
    
}
