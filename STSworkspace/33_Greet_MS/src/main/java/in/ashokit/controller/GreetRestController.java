package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.feign.WelcomeFeignClient;

@RestController
public class GreetRestController {
	@Autowired
	private WelcomeFeignClient welcomeFeign;
	
	@GetMapping("/greet")
	public String getGreetMsg() {
//		String apiUrl = "http://localhost:8081/welcome";// Now I am communicating with this MS i.e running on this port
//											//hardcoded URL
//		String greetmsg= "Good Evening Aman Sir !!!!!";
//		RestTemplate rt= new RestTemplate();
//		ResponseEntity<String> forEntity = rt.getForEntity(apiUrl, String.class);// Here Communication is DONE
//		String welcomeMsg = forEntity.getBody();
		//return greetmsg + ", "+welcomeMsg;
		
		String greetMsg= "Good Evening Aman Sir !!!!!";
		String welcomeMsg =welcomeFeign.invokeWelcomeMsg();
		
		return welcomeMsg+"- "+greetMsg;// InterService communication without specifying the URl
									// FeignClient will get the URL from the EUREKA Server
		
	}
}
