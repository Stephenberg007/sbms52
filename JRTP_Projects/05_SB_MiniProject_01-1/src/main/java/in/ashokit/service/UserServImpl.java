package in.ashokit.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import in.ashokit.binding.QuoteDTO;
import in.ashokit.binding.ResetPasswordDTO;
import in.ashokit.binding.UserRegistrationDTO;
import in.ashokit.entity.CityEntity;
import in.ashokit.entity.CountryEntity;
import in.ashokit.entity.StateEntity;
import in.ashokit.entity.UserEntity;
import in.ashokit.repo.CityRepo;
import in.ashokit.repo.CountryRepo;
import in.ashokit.repo.StateRepo;
import in.ashokit.repo.UserRepo;

@Service
public class UserServImpl implements UserServ {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CountryRepo counRepo;
	
	@Autowired
	private StateRepo statRepo;
	
	@Autowired
	private CityRepo cityRepo;
	
	@Override
	public UserRegistrationDTO login(UserRegistrationDTO userRegDto) {
		
		UserEntity user = userRepo.findByUserEmailAndUserPwd(userRegDto.getUserEmail(), userRegDto.getUserPwd());
		
		if(user != null) {
			//user.setPwdUpdated("No");
			UserRegistrationDTO userDto = new UserRegistrationDTO();
			BeanUtils.copyProperties(user, userDto);
			return userDto;
		}else {
		
		return null;
	}
	}

	@Override
	public boolean registerUser(UserRegistrationDTO userRegDto) {
		
		//String randomPwd = getRandomPwd();
		//entity.setPwd(randomPwd); or userRegDto.setPwd(randomPwd)
		//entity.setPwdUpdated("no",false);userDto.setpwdUpdated(false);
		UserEntity user = new UserEntity();
		BeanUtils.copyProperties(userRegDto, user);
	 CountryEntity country = counRepo.findById(userRegDto.getCountryId()).get();
		 user.setCountry(country);
		 StateEntity state = statRepo.findById(userRegDto.getStateId()).get();
		 user.setState(state);
		 CityEntity city = cityRepo.findById(userRegDto.getCityId()).get();
		 user.setCity(city);
		 user.setPwdUpdated("No");
		UserEntity registeredUser = userRepo.save(user);
		return registeredUser.getUserId() != null;
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryEntity> all = counRepo.findAll();// all is a collection of List of Objects of CountryEntity TYPE
		HashMap<Integer,String> counMap= new HashMap<>();
		for(CountryEntity country : all) {
			counMap.put(country.getCountryId(), country.getCountryName());
		}
		return counMap;
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		 List<StateEntity> all = statRepo.findByCountryCountryId(countryId);
		 Map<Integer,String> statMap = new HashMap<>();
		 for(StateEntity state: all) {
			 statMap.put(state.getStateId(), state.getStateName());
		 }
		
		
		return statMap;
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		List<CityEntity> all = cityRepo.findByStateStateId(stateId);
		Map<Integer,String> cityMap = new HashMap<>();
		 for(CityEntity city: all) {
			 cityMap.put(city.getCityId(),city.getCityName());
		 }
		 return cityMap;
		
		
	}

	@Override
	public boolean uniqueEmailCheck(String email) {
		UserEntity user = userRepo.findByUserEmail(email); // or directly I can Write return null == userRepo.findByUserEmail(email);
		if(user==null) {
			return true;
		}else {
		return false;
	}
	}

	@Override
	public boolean resetPassword(ResetPasswordDTO resetPwdDto) {
		String userEmail=resetPwdDto.getEmail();
		String oldPwd=resetPwdDto.getOldPassword();
		String cnfPwd=resetPwdDto.getConfirmPassword();
		String newPwd=resetPwdDto.getNewPassword();
		
		UserEntity user = userRepo.findByUserEmail(userEmail);
			/*if(user != null) {
				if(oldPwd.equals(user.getUserPwd())){
					if(cnfPwd.equals(newPwd)) {
						user.setUserPwd(newPwd);
						user.setPwdUpdated(true);
					}
						}
				
					}else {
							return false;
							}
		return true;*/
		if(user ==null) {
			return false;
		}
		if(!oldPwd.equals(user.getUserPwd())) {//If the passwords do not match, the condition inside the if statement becomes true, 
												//and the code inside the block (return false;) is executed.
			return false;
		}
		if(!cnfPwd.equals(newPwd)) {
			return false;
		}
		// if none of the above if satisfies condition
		
		user.setUserPwd(newPwd);
		user.setPwdUpdated("Yes");
		userRepo.save(user);
		return true;
		
		
	}

	@Override
	public QuoteDTO getQuotation() {
		String providerUrl = "https://dummyjson.com/quotes/random";
		RestTemplate rt = new RestTemplate();
		ResponseEntity<QuoteDTO> quote= rt.getForEntity(providerUrl, QuoteDTO.class);
		QuoteDTO body = quote.getBody();
		return body;
	}

}
