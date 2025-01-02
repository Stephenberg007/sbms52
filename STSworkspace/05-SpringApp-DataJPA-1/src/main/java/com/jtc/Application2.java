package com.jtc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jtc.entity.Product;
import com.jtc.repository.StudentRepository1;

@SpringBootApplication
public class Application2 {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application2.class, args);
		StudentRepository1 productRepo = ctx.getBean(StudentRepository1.class);
		
		
		
		Product prod2= new Product("cursor",336.56);
		productRepo.save(prod2);
		
}
}