package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.Student;

public interface StudentService {
	public String upsert(Student student);
	public Student findStudentById(Integer id);
	public String deleteById(Integer id);
	public List<Student> getAllStudent();

}
