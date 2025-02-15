package in.ashokit.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "Welcome-MS")//its case-INsensitive, WELCOME-MS is also working
public interface WelcomeFeignClient {
	
	@GetMapping("/welcome") //we are sending a GET request to this URL belonging to the MicroService with name 32_Welcome_MS
	public String invokeWelcomeMsg();//url must be the exact one in the MS we want to communicate with

}
 