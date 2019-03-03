package ru.ncedu.schek.shop.restentities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ru.ncedu.schek.shop.entities.Model;
import ru.ncedu.schek.shop.entities.Phone;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name= "MODELFORREST")
public class ModelForRest {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long modelId;

    @Column(name = "NAME", length = 64, nullable = false)
    private String  modelName;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<PhoneForRest> phones;

    @OneToMany(mappedBy = "model", cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    private List<PictureForRest> pictures;

    @Column(name = "PRICE_MIN", nullable = false)
    private long priceMin;

    @Column(name = "PRICE_MAX", nullable = false)
    private long priceMax;

    public ModelForRest(String modelName,long priceMin,long priceMax) {
        this.modelName=modelName;
        this.priceMax=priceMax;
        this.priceMin=priceMin;
    }
    public ModelForRest() {
    }

    public ModelForRest(Model e) {
    	this.modelId = e.getId();
		this.modelName = e.getName();
		Long maxprice = (long) 0;
		for (Phone p:e.getPhones()) {
			if (p.getPrice() > maxprice) {
				maxprice = p.getPrice();
			}
		}
		Long minprice = maxprice;
		for (Phone p:e.getPhones()) {
			if (p.getPrice() < minprice) {
				minprice = p.getPrice();
			}
		}
		this.priceMax = maxprice;
		this.priceMin = minprice;
	}
    
	@Override
    public String toString() {
        return "Model{" +
                "priceMax=" + priceMax +
                ", priceMin=" + priceMin +
                ", phones=" + phones +
                ", modelName='" + modelName + '\'' +
                ", modelId=" + modelId +
                '}';
    }
	public Long getModelId() {
		return modelId;
	}
	public void setModelId(Long modelId) {
		this.modelId = modelId;
	}
	public String getModelName() {
		return modelName;
	}
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	public Set<PhoneForRest> getPhones() {
		return phones;
	}
	public void setPhones(Set<PhoneForRest> phones) {
		this.phones = phones;
	}
	public List<PictureForRest> getPictures() {
		return pictures;
	}
	public void setPictures(List<PictureForRest> pictures) {
		this.pictures = pictures;
	}
	public long getPriceMin() {
		return priceMin;
	}
	public void setPriceMin(long priceMin) {
		this.priceMin = priceMin;
	}
	public long getPriceMax() {
		return priceMax;
	}
	public void setPriceMax(long priceMax) {
		this.priceMax = priceMax;
	}
    
    
}
