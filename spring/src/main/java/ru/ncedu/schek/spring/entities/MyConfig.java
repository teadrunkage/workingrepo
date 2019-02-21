package ru.ncedu.schek.spring.entities;

import org.springframework.context.annotation.*;

@Configuration
public class MyConfig {
	@Bean
	public Cat getCat() {
		return new Cat();
	}
	@Bean
	public Dog getDog() {
		return new Dog();
	}
}
