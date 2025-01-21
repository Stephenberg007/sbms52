package in.ashokit.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "WELCOME-MS")
public interface WelcomeFeignClient {
	
	@GetMapping("/welcome") //we are sending a GET request to this URL belonging to the MicroService with name 32_Welcome_MS
	public String invokeWelcomeMsg();

}
 