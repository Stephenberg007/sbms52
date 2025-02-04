package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.model.User;
import in.ashokit.service.UserService;

@RestController
public class UserRestController {

	@Autowired
	private UserService userServ;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user){
		boolean saveUser = userServ.saveUser(user);
		if(saveUser) {
			return new ResponseEntity<>("User Saved",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("Not Saved",HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	public void sendEmail() {
		// logic to send it
	}
	
}
