package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.MsgService;

@RestController
public class MsgRestController {
	
	@Autowired
	private MsgService msgService; // For Unit Testing We will Inject it in Test Class But its Dummy Object
	

	@GetMapping("/welcome")
	public String getWelcome() {
		System.out.println(msgService.getClass().getName());
		String welcomeMsg = msgService.getWelcomeMsg();
			welcomeMsg=	welcomeMsg.toUpperCase();// To check this logic in Unit testing we sent a dummy response
		
		return welcomeMsg;
		
	}
	
	@GetMapping("/greet")
	public String getGreet() {
		String greetMsg = msgService.getGreetMsg();
		System.out.println("Test Cases");
		greetMsg= greetMsg.toLowerCase();
		return greetMsg;
	}

}
