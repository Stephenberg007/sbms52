package in.ashokit.rest;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.ex.ExInfo;

@RestController
public class MsgRestController {
	
	@GetMapping("/welcome")
	public String getWelcomeMsg() {
		 int i=10/0;
		String msg = "Welcome To REST Api";
		return msg;
	}
	@GetMapping("/greet")
	public Integer getGreetMsg() {
		int i=10/0;
		String msg = null;
		return msg.length();
	}
	
	@ExceptionHandler(value= Exception.class)	//class level Exception Handler
	public ResponseEntity<ExInfo> handleException(Exception e) {
		String message = e.getMessage();
		ExInfo exInfo= new ExInfo();
		exInfo.setExCode("EX110193");
		exInfo.setExMsg(message);
		exInfo.setExDate(LocalDateTime.now());
		
		return new ResponseEntity<>(exInfo,HttpStatus.INTERNAL_SERVER_ERROR);
	
	
	}
	/*@ExceptionHandler(value=Exception.class)
	public String handleException(Exception e) {		// its also working, but i am nnot storing it as an o
		return e.getMessage();
	}*/
	
}
