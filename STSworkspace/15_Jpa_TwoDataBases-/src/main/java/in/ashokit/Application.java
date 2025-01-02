package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.primary.entity.UserEntity;
import in.ashokit.primary.repository.UserRepository;
import in.ashokit.secondary.entity.BookEntity;
import in.ashokit.secondary.repository.BookRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		UserRepository userRepo = ctxt.getBean(UserRepository.class);
		BookRepository bookRepo = ctxt.getBean(BookRepository.class);
		UserEntity u = new UserEntity();
		u.setUid(101);
		u.setUname("Aman");
		userRepo.save(u);
		BookEntity b = new BookEntity();
		b.setBid(202);
		b.setBname("Alchemist");
		bookRepo.save(b);
		
	}

}
