package in.ashokit.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ashokit.binding.AllPassengers;
import in.ashokit.binding.Passenger;

@Service
public class PassengerService {
	RestTemplate rt = new RestTemplate();
	
	public Passenger[] getAllPassengers(){
		String provUrl = "https://classes.ashokit.in/tickets";// this is the URL for GET request as provided bY swagger document
		ResponseEntity<Passenger[]> allpassengers = rt.getForEntity(provUrl, Passenger[].class);
		 Passenger[] passengers = allpassengers.getBody();
		return passengers;
	}
	
	
	public Passenger bookTicket(Passenger passenger) {
		String provUrl = "https://classes.ashokit.in/ticket";
		ResponseEntity<Passenger> bookTkt = rt.postForEntity(provUrl, passenger, Passenger.class);
		
		
		return bookTkt.getBody();
		
	}
	
}
