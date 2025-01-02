package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.service.PersonPassportService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		PersonPassportService bean = ctxt.getBean(PersonPassportService.class);
		bean.saveData();
		//bean.getPassport();
		//bean.deleteRecord();
		
	}

}
