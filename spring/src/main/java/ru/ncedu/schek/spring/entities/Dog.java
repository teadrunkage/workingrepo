package ru.ncedu.schek.spring.entities;

public class Dog {
	private String name;
	
	public Dog() {
		this.name = "Noname_dog";
	}
	public Dog(String name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}
