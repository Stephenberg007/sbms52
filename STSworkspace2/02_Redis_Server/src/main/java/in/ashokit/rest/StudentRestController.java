package in.ashokit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.Student;
import in.ashokit.repo.StudentRepo;

@RestController
public class StudentRestController {
	
	@Autowired
	private StudentRepo sRepo;
	
	@PostMapping("/student")
	public String addStudent(@RequestBody Student student) {
		Student save = sRepo.save(student);
		return "Student Saved";
		                          
	}
	
	@GetMapping("/getStudents")// to retrieve data from Redis
	public Iterable<Student> getAllStudents(){
		return sRepo.findAll();
	}
	
	@Cacheable(value="students",key="id")
	@GetMapping("/student/{id}")
	public Student getStudents(@PathVariable Integer id) {
		return sRepo.findById(id).get();
	}
	
}
