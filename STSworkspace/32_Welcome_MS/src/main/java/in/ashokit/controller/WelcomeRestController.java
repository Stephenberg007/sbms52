package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {
	@Autowired
	private Environment env;// video 52
	
	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		String port = env.getProperty("server.port");//for video 52
		String msg = "Welcome To Aman Furniture (" + port + ")";
		return msg;
	}
}
