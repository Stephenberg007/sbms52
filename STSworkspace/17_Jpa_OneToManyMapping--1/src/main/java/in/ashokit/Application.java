package in.ashokit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import in.ashokit.service.EmployeeService;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctxt = SpringApplication.run(Application.class, args);
		EmployeeService bean = ctxt.getBean(EmployeeService.class);
		//bean.addEmployee();
		bean.getEmpBySalary(25000l);
		//bean.GetEmployeeByCity("Delhi");
	}

}
