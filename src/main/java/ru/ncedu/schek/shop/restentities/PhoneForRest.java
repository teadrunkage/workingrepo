package ru.ncedu.schek.shop.restentities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import ru.ncedu.schek.shop.entities.Phone;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneForRest {
    private Long phoneId;

    private ModelForRest model;

    private long price;

    private String color;
    
    private String link;
    
    private String picturelink;

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
    	this.link = "http://localhost:8081/guestphonepage?phoneId="+p.getId();
		this.model= new ModelForRest(p.getModelName(),p.getPrice(), p.getPrice());
    	this.color = p.getColor();
    	this.price = p.getPrice();
    	//картинка
    	String picturelink = "";
		try {
			picturelink = "data:image/jpeg;base64,"+p.getPicture().encodeImage();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	this.picturelink = picturelink;
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

	public String getPicturelink() {
		return picturelink;
	}

	public void setPicturelink(String picturelink) {
		this.picturelink = picturelink;
	}
    
    
}
