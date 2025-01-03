package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.entity.Student;
import in.ashokit.repository.StudentRepo;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		StudentRepo stuRepo = ctxt.getBean(StudentRepo.class);
		Student s1 = new Student("aman1","Doon1");
		stuRepo.save(s1);
		Student s2 = new Student("aman2","Doon2");
		Student s3 = new Student("aman3","Doon3");
		Student s4 = new Student("aman4","Doon4");
		stuRepo.save(s2);
		stuRepo.save(s3);
		stuRepo.save(s4);
		int updateStudent = stuRepo.updateStudent("Raghav", 1);
		System.out.println(updateStudent);
		
	}

}
