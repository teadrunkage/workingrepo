package ru.ncedu.schek.spring.entities;

public class Cat {
	private String name;
	
	public Cat() {
		this.name = "Noname_cat";
	}
	public Cat(String name) {
		this.name = name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}

}
