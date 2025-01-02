package in.ashokit.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.binding.Student;
import in.ashokit.repo.StuRepo;
@Service
public class StudentServiceImpl implements StudentService {
	@Autowired
	private StuRepo stuRepo;
	@Override
	public String upsert(Student student) {
	
		stuRepo.save(student);
	
		return "Record Added/updated";
	}

	@Override
	public Student findStudentById(Integer id) {
		Optional<Student> byId= stuRepo.findById(id);
		if(byId.isPresent())
			return byId.get();
		else
		return null;
	}

	@Override
	public String deleteById(Integer id) {
		if(stuRepo.existsById(id)) {
		stuRepo.deleteById(id);
		return "Record Deleted";
		}else {
		return "Record Not Found";
		}
	}

	@Override
	public List<Student> getAllStudent() {
		List<Student> students = stuRepo.findAll();
		return students;
	}

}
