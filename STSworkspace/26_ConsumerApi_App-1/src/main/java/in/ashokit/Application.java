package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import in.ashokit.service.MsgService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		MsgService msgBean = ctxt.getBean(MsgService.class);
		//msgBean.getApiResponse();
		msgBean.getRandomQuote();
		System.out.println("---------------------------------------------------------");
		//msgBean.getAllQuotes();
		
	}

}
