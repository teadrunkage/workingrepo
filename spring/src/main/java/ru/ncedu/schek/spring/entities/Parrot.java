package ru.ncedu.schek.spring.entities;

import org.springframework.stereotype.Component;

@Component
public class Parrot {
	private String name;
	
	public Parrot() {
		this.name = "Noname_dog";
	}
	public Parrot(String name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}