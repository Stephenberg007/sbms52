package com.jtc;

import java.util.List; 
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import com.jtc.Entity.Student;
import com.jtc.Repository.StudentRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		StudentRepository studentRepo = context.getBean(StudentRepository.class);
		
		/*
		 * // Student s = new Student(); // s.setId(106); // s.setGender("Male"); //
		 * s.setName("Aman Maurya"); // s.setRank(1145l); // studentRepo.save(s); // //
		 * Student s1 = new Student(102,"Anuj",1564l,"feMale"); // Student s2 = new
		 * Student(103,"Niti",2236l,"Female"); // Student s3 = new
		 * Student(104,"Ankit",897l,"Male"); // // List<Student> students =
		 * Arrays.asList(s1,s2,s3); // studentRepo.saveAll(students);
		 */		
		Scanner s = new Scanner(System.in);
		System.out.print("Enter Page Number");
		int pageNo=s.nextInt();
		int size=3;
		
		//PageRequest pageReq = PageRequest.of(pageNo-1, size);
		PageRequest pageReq= PageRequest.of(pageNo-1,size,Sort.by("name"));
		Student stu = new Student();
		stu.setGender("Male");
		Example<Student> example = Example.of(stu);
		Page<Student> page = studentRepo.findAll(example, pageReq);//Here I combined all 3 i.e pagination,sorting and QBE
		List<Student> students = page.getContent();
		
		students.forEach(System.out::println);
	}

}
