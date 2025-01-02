package com.jtc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jtc.Entity.Student;

public interface StudentRepository extends JpaRepository<Student,Integer> {

}
