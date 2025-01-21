package in.ashokit.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
	
	@GetMapping(value="/first",
			produces="application/json"
				
			)
		public String getMsg() {
		
			return "Welcome To REST APIs";
		}
	@GetMapping(value="/greet",produces="application/xml")
	public ResponseEntity<String> getGreetMsg(){
			String msg= "<h1>Good Morning...!!</h1>" ;
			return new ResponseEntity(msg,HttpStatus.OK);
		//return new ResponseEntity(msg,HttpStatus.TEMPORARY_REDIRECT);
	}
	

}
