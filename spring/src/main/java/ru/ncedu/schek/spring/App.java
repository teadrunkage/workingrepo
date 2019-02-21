package ru.ncedu.schek.spring;

import org.springframework.context.*;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.ncedu.schek.spring.entities.*;

public class App {
	public static void main( String[] args )
    {
        System.out.println( "Hello World!" );  
        ApplicationContext context =
        		new AnnotationConfigApplicationContext("ru.ncedu.schek.spring.entities");
        
        Cat cat = context.getBean(Cat.class);
        Dog dog = context.getBean(Dog.class);
        Parrot parrot = context.getBean(Parrot.class);
        cat.setName("Murka");
        dog.setName("Woof");
        parrot.setName("Inokentiy");

    	System.out.println(cat.getName());
    	System.out.println(dog.getName());
    	System.out.println(parrot.getName());
    }

}
