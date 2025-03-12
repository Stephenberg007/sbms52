package in.ashokit.service;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPasswordDto;
import in.ashokit.response.AuthResponse;

public interface CustomerService {
	public CustomerDto registerCustomer(CustomerDto customerDto);
	public AuthResponse login(CustomerDto customerDto);
	public Boolean resetPassword(ResetPasswordDto resetpwdDto);
	public CustomerDto getCustomerData(String custEmail);
	public Boolean forgotPwd(String email);
	public Boolean isEmailUnique(String Email);
	

}
