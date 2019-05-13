package ru.ncedu.schek.shop.entities;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;


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
    @Lob
    @Column(name = "COMMENT", nullable = true)
    private String comment;
    @Column(name = "PRICE")
    private Long price;
    
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
    	this.price = (long) 10000;
    }
    
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

	public Phone(Model model, String color, Long price) {
    	this.model = model;
    	this.color = color;
    	this.creationDate = new Date();
    	this.price = price;
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
	
	public void addUser(User user) {
		this.users.add(user);
	}
	
 
}
