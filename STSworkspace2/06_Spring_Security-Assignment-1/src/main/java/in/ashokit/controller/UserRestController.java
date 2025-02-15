package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.entity.UserEntity;
import in.ashokit.service.UserService;

@RestController
public class UserRestController {
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody UserEntity usrDetails){
		UsernamePasswordAuthenticationToken token=
				new UsernamePasswordAuthenticationToken(usrDetails.getEmail(),usrDetails.getPwd());
		
		
		Authentication authenticate = authManager.authenticate(token);//Performing Authentication and an Authentication Object is created.
		boolean status = authenticate.isAuthenticated();
		if(status) {
			// In Real Time We would Generate JWT here and send to client
		return new ResponseEntity<>("Login Success",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Login Failed",HttpStatus.FORBIDDEN);
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerCustomer(@RequestBody UserEntity user){//BCoz I do not Have a form IN UI to Recieve USER INPUTS
		Boolean status = userServ.saveUser(user);
		if(status) {
			return new ResponseEntity<>("Registration Done",HttpStatus.CREATED);
			
		}else {
			return new ResponseEntity<>("Registration Failed",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	
	}
	
}
