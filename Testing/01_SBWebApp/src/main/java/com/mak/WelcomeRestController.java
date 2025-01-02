package com.mak;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeRestController {
	@GetMapping("/")
	public String getWelcomeMsg() {
		return "Welcome to Java World";
	}
}
