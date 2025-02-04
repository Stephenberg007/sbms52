package in.ashokit.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.model.User;
import in.ashokit.model.UserResponse;

@RestController
public class DemoRestController {
	
	@PostMapping("/recieve")
	public String getUser(@RequestBody User user) {
		String userName=user.getName();
		Long salary=user.getSalary();
		String city= user.getCity();
		System.out.println("---> "+user);
		return "User Details "+userName+" ,, salary "+salary+" ,,city"+city;
	}
	
	@PostMapping("/officer")
	//public ResponseEntity<UserResponse> getOfficer(@RequestBody User user) {
	public UserResponse getOfficer(@RequestBody User user) {
		UserResponse ur= new UserResponse();
		ur.setOfficerArea(user.getCity());
		ur.setOfficeRent(user.getSalary());
		ur.setOfficerName(user.getName());
		System.out.println(ur);// here it is object 
		return ur;
		//return new ResponseEntity<>(ur,HttpStatus.CREATED);// but when i am sending it as response it becomes JSON
	}
}
