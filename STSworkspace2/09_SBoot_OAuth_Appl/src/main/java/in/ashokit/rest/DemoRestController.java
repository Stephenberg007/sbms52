package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController// Visit properties file and remove # done bcoz git was not saving secrets
public class DemoRestController {
	
	@GetMapping("/")
	public String getMsg() {
		return "O auth app Test";
	}
	@GetMapping("/google")
	public String getStringMsg() {
		return "O Auth Msg Using Google";
	}
	
	
}
