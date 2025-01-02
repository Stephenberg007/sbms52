package in.ashokit.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.binding.Student;

public interface StuRepo extends JpaRepository<Student,Serializable> {

}
