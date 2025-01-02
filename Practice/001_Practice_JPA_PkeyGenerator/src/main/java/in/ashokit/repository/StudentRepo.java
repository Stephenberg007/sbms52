package in.ashokit.repository;

import org.springframework.data.jpa.repository.Modifying;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import in.ashokit.entity.Student;
import jakarta.transaction.Transactional;

public interface StudentRepo extends CrudRepository<Student,Integer> {
	@Query("update Student set name=:name where sid=:sid")
	@Modifying
	@Transactional
	public int updateStudent(String name,int sid);

}
