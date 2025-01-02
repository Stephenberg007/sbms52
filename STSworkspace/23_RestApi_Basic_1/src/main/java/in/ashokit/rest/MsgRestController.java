package in.ashokit.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MsgRestController {
	
	@GetMapping(value="/first",
				produces="text/plain"
			)
		public String getMsg() {
		
			return "Welcome To REST APIs";
		}
	@GetMapping(value="/greet",produces="text/plain")
	public ResponseEntity<String> getGreetMsg(){
			String msg= "Good Morning...!!" ;
			return new ResponseEntity(msg,HttpStatus.OK);
		//return new ResponseEntity(msg,HttpStatus.TEMPORARY_REDIRECT);
	}
	

}
