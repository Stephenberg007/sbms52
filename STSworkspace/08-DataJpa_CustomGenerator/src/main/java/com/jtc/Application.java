package com.jtc;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jtc.entity.OrderDetailsEntity;
import com.jtc.repository.OrderDetailsRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		OrderDetailsRepository orderRepo = ctxt.getBean(OrderDetailsRepository.class);
		OrderDetailsEntity entity = new OrderDetailsEntity();
		entity.setOrderBy("AShok_Sir");
		entity.setOrderPlacedDate(new Date());
		OrderDetailsEntity savedEntity = orderRepo.save(entity);
		System.out.println(savedEntity);
		
		ctxt.close();
	
	}

}
