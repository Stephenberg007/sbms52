package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.Service.ReportService;
import in.ashokit.Service.UserService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		UserService bean = ctxt.getBean(UserService.class);
		bean.saveUser();
		ReportService bean2 = ctxt.getBean(ReportService.class);
		bean2.reportService();
	}

}
