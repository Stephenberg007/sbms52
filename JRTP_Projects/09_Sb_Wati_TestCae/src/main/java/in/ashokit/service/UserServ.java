package in.ashokit.service;

import in.ashokit.dto.UserDto;
import in.ashokit.dto.WatiRequest;
import in.ashokit.dto.WatiResponse;

public interface UserServ {
	public boolean saveUser(UserDto dto);
	public boolean validateOtp(UserDto dto);
	public boolean generateOtp();
	
	public WatiResponse sendOtp(WatiRequest request);

}
