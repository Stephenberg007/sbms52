package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPasswordDto;
import in.ashokit.response.ApiResponse;
import in.ashokit.response.AuthResponse;
import in.ashokit.service.CustomerServImpl;

@RestController
public class CustomerController {

	private final CustomerServImpl custServ;

	
	private final BCryptPasswordEncoder pwdEncoder;

	@Autowired
	public CustomerController(CustomerServImpl custServ,
			BCryptPasswordEncoder pwdEncoder) {
		this.custServ = custServ;
		this.pwdEncoder = pwdEncoder;
	}

	@PostMapping("/register")
	public ResponseEntity<ApiResponse<CustomerDto>> saveUser(@RequestBody CustomerDto customerDto) {
		CustomerDto savedCustomer = custServ.registerCustomer(customerDto);
		if (savedCustomer != null) {
			ApiResponse<CustomerDto> response = new ApiResponse<>();
			response.setStatus(201);
			response.setMessage("Customer Record Saved");
			response.setData(savedCustomer);
			return new ResponseEntity<>(response, HttpStatus.CREATED);

		} else {
			ApiResponse<CustomerDto> response = new ApiResponse<>();
			response.setStatus(500);
			response.setMessage("User Already Exists");
			response.setData(null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}
	
	

	@PostMapping("/login")
	public ResponseEntity<ApiResponse<AuthResponse>> login(@RequestBody CustomerDto customerDto) {
		ApiResponse<AuthResponse> response = new ApiResponse<>();
		AuthResponse authResp = custServ.login(customerDto);

		if (authResp != null) {
			response.setStatus(200);
			response.setMessage("Login Success");
			response.setData(authResp);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.OK);
		} else {
			response.setStatus(400);
			response.setMessage("Invalid Credentials");
			response.setData(null);
			return new ResponseEntity<ApiResponse<AuthResponse>>(response, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/resetPassword")
	public ResponseEntity<ApiResponse<String>> resetPassword(@RequestBody ResetPasswordDto resetPwdDto) {
		ApiResponse<String> response = new ApiResponse<>();
		CustomerDto customer = custServ.getCustomerData(resetPwdDto.getCustEmail());
		if (!pwdEncoder.matches(resetPwdDto.getOldPassword(), customer.getPwd())) {
			response.setStatus(400);
			response.setMessage("Failed");
			response.setData("Wrong Credentials Entered");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Boolean status = custServ.resetPassword(resetPwdDto);
		if (status) {

			response.setStatus(200);
			response.setMessage("Operation Performed");
			response.setData("Password Updated");
			return new ResponseEntity<>(response, HttpStatus.OK);

		} else {

			response.setStatus(500);
			response.setMessage("Wrong Credentials Entered");
			response.setData("Password Reset Failed");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/forgotPassword/{email}")
	public ResponseEntity<ApiResponse<String>> forgotPassword(@PathVariable String email) {
		ApiResponse<String> response = new ApiResponse<>();
		Boolean request = custServ.forgotPwd(email);
		if (request) {
			response.setStatus(200);
			response.setMessage("Approved");
			response.setData("An email with reset password link sent to Your Email");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.setStatus(500);
			response.setMessage("Wrong Credentials Entered");
			response.setData("Email Not Registerd with us. Please Register and Login");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

	}

}
