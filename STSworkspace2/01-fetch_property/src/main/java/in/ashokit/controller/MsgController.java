package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.prop.AppProperties;
import in.ashokit.prop.ApplProperties;

@RestController
public class MsgController {
	
	@Value("${salary}")
	private Long salary;
	
	@Autowired
	private AppProperties props;
	
	@Autowired
	private ApplProperties propps;
	
	@GetMapping("/")
	public Long returnSalary() {
		
		return salary;
	}
	
	@GetMapping("/logerror")
	public String getLogError() {
		Map<String, String> tango = props.getXyz();// we are getting a MAP of all the messages available in the property file
		System.out.println(tango);// when i used 'meme' as name of MAP then I was getting all the 6 properties here
		return tango.get("login_error");
	}
	
	
	  @GetMapping("/regerror")
	  public String getRegistrationError() {
		  Map<String, String> msg = props.getXyz(); 
		  System.out.println(msg);
	 
	 return msg.get("register_error");
	 }
	  
	  @GetMapping("/pymt")
	  public String getPaymentError() {
		  Map<String, String> messages = propps.getCutory(); //it worked
		  System.out.println(messages);
	 
	 return messages.get("payment_error");
	 }
	 
	
	
}
