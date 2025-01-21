package in.ashokit.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
public class DataRestController {
	
	@GetMapping("/data")
	@CircuitBreaker(fallbackMethod="getDataFromDB", name="FirstCB")
	public String getDataFromRedis() {
		
		System.out.println("***Redis () Called***");
		int i=10/0; // Arrangement to throw an exception so that fallBack logic Executes
		// TO DO: Reddis Connection Logic
		return "getting Data From Redis";
	}
	public String getDataFromDB(Throwable t) {	// Have not used any URL but still this method is being called when Exceptios are being encountered by main logic
		System.out.println("***DB() is called***");
		// DB Connection logic
		
		return "Fetching from DB";
	}
}
