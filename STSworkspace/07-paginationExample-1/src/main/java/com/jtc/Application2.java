package com.jtc;

import java.util.List;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.jtc.Entity.Student;
import com.jtc.Repository.StudentRepository;

@SpringBootApplication
public class Application2 {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application2.class, args);
		StudentRepository studentRepo = context.getBean(StudentRepository.class);
		Student s = new Student();
		s.setGender("Male");
		//s.setRank(100l);
		Example<Student> example = Example.of(s);
		// List<Student> students = studentRepo.findAll(example);
		//List<Student> students = studentRepo.findAll(example, Sort.by("name"));//example and sorting together
		Scanner input = new Scanner(System.in);
		System.out.println("Enter your Input");
		int pageNo=input.nextInt();
		PageRequest pageReq = PageRequest.of(pageNo-1, 3);
		Page<Student> page = studentRepo.findAll(example, pageReq);
		List<Student> students = page.getContent();
		students.forEach(System.out::println);
		
		
	}
}