package in.ashokit.service;

import java.util.Map;

import in.ashokit.binding.QuoteDTO;
import in.ashokit.binding.ResetPasswordDTO;
import in.ashokit.binding.UserRegistrationDTO;

public interface UserServ {
	public UserRegistrationDTO login(UserRegistrationDTO userRegDto);
	
	public boolean registerUser(UserRegistrationDTO userRegDto);
	public Map<Integer,String> getCountries();
	public Map<Integer, String> getStates(Integer countryId);
	public Map<Integer,String> getCities(Integer stateId);
	public boolean uniqueEmailCheck(String email);
	
	public boolean resetPassword(ResetPasswordDTO resetPwdDto);
	
	public QuoteDTO getQuotation();

	//Map<Integer, String> getCities(Integer stateId);
	
 
}
