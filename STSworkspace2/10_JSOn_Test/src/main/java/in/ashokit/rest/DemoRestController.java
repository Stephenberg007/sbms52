package in.ashokit.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
	@GetMapping("/welcome")
	public User getMsg() {
		User user = new User();
		user.setCity("Dehradun");
		user.setName("Aniket");
		user.setSalary(85000l);
		System.out.println(user);
		return user;// response came as JSON on Browser, but as an Object on Console, beczuz of Object binding
		
	}
	@GetMapping("/json")	
	public String getApiResponse() {
		RestTemplate rt = new RestTemplate();
		//String providerUrl = "http://localhost:8080/loginf";
		String providerUrl="https://dummyjson.com/quotes/random";
		ResponseEntity<String> forEntity = rt.getForEntity(providerUrl, String.class);
		int value = forEntity.getStatusCode().value();
		String body = forEntity.getBody();
		System.out.println("Status Code Value :-"+value+" BODY :-"+body);
		return body+value;// Response came as JSON on BROWSER+on console
	}

}
