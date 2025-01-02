package com.jtc;

import java.util.List;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Sort;

import com.jtc.Entity.Student;
import com.jtc.Repository.StudentRepository;

@SpringBootApplication
public class Application1 {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application1.class, args);
		StudentRepository studentRepo = context.getBean(StudentRepository.class);
		List<Student> students= studentRepo.findAll(Sort.by("name"));
		//List<Student> students= studentRepo.findAll(Sort.by("gender"));
		students.forEach(System.out::println);
		
	}
}