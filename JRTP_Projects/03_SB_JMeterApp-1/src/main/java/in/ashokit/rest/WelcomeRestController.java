package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class WelcomeRestController {

	@GetMapping("/welcome")
	public String getMsg() {
		log.info("***********welcomeMsg() - started*************");
		//logic
		log.info("***********welcomeMsg() - ended*************");

		return "Welcome to Ashok IT";
	}

}