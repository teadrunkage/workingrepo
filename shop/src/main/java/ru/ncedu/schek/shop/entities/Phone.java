package ru.ncedu.schek.shop.entities;


import java.util.Date;
import java.util.Set;

import lombok.Data;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;


@Entity
@Data
@Table(name = "PHONES")
public class Phone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PHONE_ID", nullable = false)
    private Long id;
	@ManyToOne
    @JoinColumn(name = "MODEL_ID", nullable = false)
    private Model model;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATION_DATE")
    private Date creationDate;
    @Column(name = "COLOR", nullable = true)
    private String color;
    
    @ManyToOne
    @JoinColumn(name = "PICTURE_ID", nullable = true)
    private Picture picture;
    
    @ManyToMany
	private Set<User> users;
 
 
    
    public Phone() { }

    public Phone(Model model) {
    	this.model = model;
    	this.color = null;
    	this.creationDate = new Date();
    }
    
	public Phone(Model model, String color) {
    	this.model = model;
    	this.color = color;
    	this.creationDate = new Date();
    }
	
	@Override
	public String toString() {
		return "ID: " + this.id+ ' ' + "Model: " + this.model.getName() + ' ' + "Creation date: " + this.creationDate;
	}
	
	
	public Date getCreationDate() {
		return this.creationDate;
	}
	
	public String getModelName() {
		return this.model.getName();
	}
	
	public String getColor() {
		return color;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
 
}
