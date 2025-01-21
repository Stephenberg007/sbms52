package in.ashokit.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.binding.Student;
import in.ashokit.service.StudentService;

@RestController
public class StudentRestController {
	@Autowired
	private StudentService stuServ;
	
	@PostMapping(
			value= "/course",
			consumes = {"application/json","application/xml"},
			produces = "text/plain"
			
			)
	public ResponseEntity<String> addRecord(@RequestBody Student student) {
		String status = stuServ.upsert(student);
		return new ResponseEntity<String>(status,HttpStatus.CREATED);
		
	}
	
	@GetMapping(value="/studento/{sid}",
			produces={"application/xml","application/json"}
			
			)
	public ResponseEntity<Student> findStudent(@PathVariable Integer sid){
		Student studnt = stuServ.findStudentById(sid);
		/*String status;
		if(studentById != null) {
			status= "Record Retrieved";
		}else {
			status ="Record do not exist";
		}*/
		return new ResponseEntity<Student>(studnt,HttpStatus.OK);
	}
	
	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudents(){
		List<Student> allStudent = stuServ.getAllStudent();
		return new ResponseEntity<>(allStudent,HttpStatus.OK);
	}
	
	@DeleteMapping("/student/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable Integer id){
		String status = stuServ.deleteById(id);
		return new ResponseEntity<>(status,HttpStatus.OK);
		
		
	}
	@PatchMapping(value = "/student",
			consumes = {"application/json","application/xml"},
			produces={"application/xml","application/json"}
			)
	public ResponseEntity<Student> updateRecord(@RequestBody Student student) {
		String status = stuServ.upsert(student);// havent used it bcoz i wanted to send and save an object
		return new ResponseEntity<Student>(student,HttpStatus.CREATED);
		
	}
	@PutMapping(value = "/student",
			consumes = {"application/json","application/xml"},
			produces={"application/xml","application/json"}
			)
	public ResponseEntity<Student> updateAllRecord(@RequestBody Student student) {
		String status = stuServ.upsert(student);// havent used it bcoz i wanted to send and save an object
		return new ResponseEntity<Student>(student,HttpStatus.CREATED);
		
	}
	
	

}
