package com.jtc;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jtc.entity.Student;
import com.jtc.repository.StudentRepository;

@SpringBootApplication
public class Application1 {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application1.class, args);
		StudentRepository studentRepo = ctx.getBean(StudentRepository.class);
		Student s1 = new Student(102,"Anuj",300l,"Female");
		Student s2 = new Student(103,"Niti",400l,"Female");
		Student s3 = new Student(104,"Rohit",897l,"Male");
		Student s4 = new Student(105,"Sumit",963488l,"Male");
		Student s5 = new Student(116,"Aman Maurya",8979l,"Male");
		
		List<Student> students = Arrays.asList(s1,s2,s3,s4,s5);
		studentRepo.saveAll(students);
		
	}
}
