package com.jtc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jtc.entity.Student;

public interface StudentRepository extends CrudRepository<Student,Integer>{
		//public List<Student> findByGender(String gender);
		
		//public List<Student> findByRank(Long rank);
		
		
}
