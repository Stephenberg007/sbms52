package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.service.EmailService;
import jakarta.mail.MessagingException;

@SpringBootApplication
public class Application {

	public static void main(String[] args) throws MessagingException{
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		EmailService bean = ctxt.getBean(EmailService.class);
		String toEmail="dharmendrakumar3185@gmail.com";
		String subject="Email-with-attachments";
		String body="Please Find Attachments";
		bean.sendEmailWithAttachment(toEmail, subject, body);
		
		
	}

}
