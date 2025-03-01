package in.ashokit;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.ClassPathResource;

import in.ashokit.controller.EmailController;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		EmailController bean = ctxt.getBean(EmailController.class);
		System.out.println("Image 1 exists: " + new ClassPathResource("Angualr_Installation.png").exists());
    	System.out.println("Image 2 exists: " + new ClassPathResource("AngularComponents.png").exists());
		//bean.sendEmail("meena510821@gmail.com", "Test Email", "Hi Mother, \n How are u doing?\n I hope all is well.");
    	System.out.println("Absolute Path: " + new File("src/main/resources/Angular_Installation.png").getAbsolutePath());

	}

}
