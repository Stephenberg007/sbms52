package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.service.MsgService;

@RestController
public class MsgRestController {
	
	@Autowired
	private MsgService msgService;
	
	@GetMapping("/greet")
	public String getMsg() {
		String msg = msgService.getMsg();
		msg=msg.toUpperCase();
		return msg;
	}
	
}
