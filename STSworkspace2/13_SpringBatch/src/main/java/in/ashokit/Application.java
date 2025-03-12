package in.ashokit;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Scheduled(fixedRate = 8000)
	public void demo() {
		System.out.println("Task Executed :- "+ new Date());
	}
	
	@Scheduled(cron ="* * 8 * * MON")
	public void demo1() {
		System.out.println("Task Executed :- "+ new Date());
	}

}
