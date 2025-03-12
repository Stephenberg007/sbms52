package com.jtc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import com.jtc.entity.Student;


public interface StudentRepository extends CrudRepository<Student,Integer> {
public List<Student> findByGender(String gender);
	
//ublic List<Student> findByRankGreaterThan(Long rank);
	//Custom Queries
//	@Query(value="select * from student_dtls where student_id>=:id",nativeQuery=true)
//	public List<Student> getAllStudents(int id);
	
	@Query(" Select rank, gender from Student")
	List<Student> getStudents();
	//@Transactional
	//@Modifying
	//@Query("Delete from Student where gender =:gender")
	//int deleteStudentsByGender(String gender);
	

}
//