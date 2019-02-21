package ru.ncedu.schek.shop.entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;


@Entity
@Table(name = "MODELS")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MODEL_ID", nullable = false)
    private Long id;
 
    @Column(name = "NAME", length = 64, nullable = false)
    private String name;
    
    @OneToMany(mappedBy = "model")
    private Set<Phone> phones;
    @OneToMany(mappedBy = "model")
    private Set<Picture> pictures = new HashSet<Picture>();
    
    public Model() {}
    
    public Model(String name) {
    	this.name = name;
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
 
    public void setName(String Name) {
        this.name = Name;
    }

	public Set<Phone> getPhones() {
		return phones;
	}

	public void setPhones(Set<Phone> phones) {
		this.phones = phones;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}
 
}
