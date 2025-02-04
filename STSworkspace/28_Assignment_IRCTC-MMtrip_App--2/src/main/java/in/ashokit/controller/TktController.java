package in.ashokit.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.Passenger;
import in.ashokit.service.PassengerService;

@Controller
public class TktController {
	@Autowired
	PassengerService pserv;
	
	@GetMapping("/ticket-view")
	public String viewTickets(Model model) {
		 Passenger[] allPassengers = pserv.getAllPassengers();
		model.addAttribute("passengers",allPassengers);
		System.out.println(Arrays.toString(allPassengers));// for printing the records on Console
		return "tickets";
		
	}
	@GetMapping("/index")
	public String bookingPage(Model model) {
		Passenger pObj = new Passenger();// Form Binding thru binding class Object
		model.addAttribute("passenger",pObj);
		return "bookticket";
		}
	
	@PostMapping("/book")
	public String bookingPage(Passenger passenger, Model model) {
		Passenger bookTicket = pserv.bookTicket(passenger);
			if(bookTicket.getTicketId() != null) {
				model.addAttribute("sMsg","Ticket Booked");
			}else {
				model.addAttribute("errMsg","Ticket Could Not Be Booked");
			}
				
		return "bookticket";
	}

	
}
