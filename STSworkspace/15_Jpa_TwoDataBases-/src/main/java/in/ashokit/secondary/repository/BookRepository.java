package in.ashokit.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.secondary.entity.BookEntity;

public interface BookRepository extends JpaRepository<BookEntity,Integer> {

}
