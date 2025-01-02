package com.jtc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
		//System.out.println(studentRepo.getClass().getName());
		Student s = new Student();
		s.setId(106);
		s.setGender("Male");
		s.setName("Aman Maurya");
		s.setRank(1145l);
		studentRepo.save(s);
		
		Student s1 = new Student(102,"Anuj",1564l,"feMale");
		Student s2 = new Student(103,"Niti",2236l,"Female");
		Student s3 = new Student(104,"Ankit",897l,"Male");
		
		List<Student> students = Arrays.asList(s1,s2,s3);
		studentRepo.saveAll(students);
		Optional<Student> optional = studentRepo.findById(101);
			if(optional.isPresent()) {
				System.out.println(optional.get());
			}
		Iterable<Student> records = studentRepo.findAll();
		//System.out.println(records);
		//for(Student stu:records) {
		//	System.out.println(stu);
		//}
		records.forEach(student->System.out.println(student));
		
		
		long count = studentRepo.count();
		System.out.println("Total Records:"+count);
		
	}

}
