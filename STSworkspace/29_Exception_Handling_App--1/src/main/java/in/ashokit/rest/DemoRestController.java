package in.ashokit.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoRestController {
	@GetMapping("/demomsg")
	public String getDemoMsg() {
		 int i=10/0;
		String msg = "Welcome To DEMO";
		return msg;
	}
}
