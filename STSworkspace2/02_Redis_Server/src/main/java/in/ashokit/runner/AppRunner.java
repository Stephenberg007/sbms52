package in.ashokit.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements ApplicationRunner {

	@Override
	public void run(ApplicationArguments args) throws Exception {// Runner executes automatically when application starts
		System.out.println("Runner()-  + I can write logic to Store DATA IN REDIS Here");
		
	}
	

}
