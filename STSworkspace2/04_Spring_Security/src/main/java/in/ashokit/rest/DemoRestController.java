package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
	
	@GetMapping("/welcome")
	public String getWelcomeMsg() {
	return "Welcome To Aman Furnitures";
	
	}
	
	@GetMapping("/greet")
	public String getGreetMsg() {
	return "Good Evening,Amigos";
	
	}
	
	@GetMapping("/contact")
	public String getContacts() {
	return "Contact Informsation";
	
	}
	
}
