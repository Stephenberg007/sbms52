package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.service.MsgService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		MsgService msgBean = ctxt.getBean(MsgService.class);
		//msgBean.getRandomQuote();
		msgBean.getRandomQuote1();
	}
	

}
