package ru.ncedu.schek.shop.restentities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import ru.ncedu.schek.shop.entities.Phone;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "PHONEFORREST")
public class PhoneForRest {
    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long phoneId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private ModelForRest model;

    @OneToMany(mappedBy = "phone", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<PictureForRest> pictures;

    @Column(name = "PRICE")
    private long price;

    @Column(name = "COLOR")
    private String color;

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
    	this.phoneId = p.getId();
    	this.color = p.getColor();
    	this.price = p.getPrice();
		//need to set model after
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
    
    
}
