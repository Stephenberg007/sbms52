package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.NotificationServImpl;

@RestController
public class NotificationRestController {
	
	@Autowired
	private NotificationServImpl notifService;
	
	@GetMapping("/send")
	public String demo() {
		notifService.sendDeliveryNotifications();
		return "success";
	}
	
	
}
