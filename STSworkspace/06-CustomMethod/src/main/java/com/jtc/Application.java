package com.jtc;

import java.util.List; 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.jtc.entity.Student;
import com.jtc.repository.StudentRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(Application.class, args);
		StudentRepository studentRepo = ctx.getBean(StudentRepository.class);
		List<Student> maleStudents = studentRepo.findByGender("Male");
		maleStudents.forEach(System.out::println);
	//	System.out.println("Rank Students");
	//	List<Student> rankStudents = studentRepo.findByRankGreaterThan(1901l);
	//	rankStudents.forEach(System.out::println);
//	List<Student> allStu = studentRepo.getStudents();
		//allStu.forEach(System.out::println);
		
//		int s = studentRepo.deleteStudentsByGender("FeMale");
//		System.out.println(s);
//		
		//List<Student> allStu = studentRepo.getStudents(100l);
	//	allStu.forEach(System.out::println);
		
	//	List<Student> maleStu= studentRepo.getStudents();
	//	maleStu.forEach(System.out::println);
	}

}//999999999+999999999999999999999999999999999999-+986+-986589-++9
