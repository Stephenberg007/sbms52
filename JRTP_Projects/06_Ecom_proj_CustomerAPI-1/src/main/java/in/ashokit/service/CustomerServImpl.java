package in.ashokit.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.ResetPasswordDto;
import in.ashokit.entity.Customer;
import in.ashokit.mapper.CustomerMapper;
import in.ashokit.repo.CustomerRepo;
import in.ashokit.response.AuthResponse;
@Service
public class CustomerServImpl implements CustomerService,UserDetailsService {
	
	private final CustomerRepo custRepo;
	private final EmailService eServ;
	private final BCryptPasswordEncoder pwdEncoder;
	private final AuthenticationManager authManager;
	
	@Autowired
	public CustomerServImpl(CustomerRepo custRepo,EmailService eServ, BCryptPasswordEncoder pwdEncoder, AuthenticationManager authManager) {
		this.custRepo=custRepo;
		this.eServ=eServ;
		this.pwdEncoder=pwdEncoder;
		this.authManager = authManager;
	}

	@Override
	public CustomerDto registerCustomer(CustomerDto customerDto) {
		//
		String email = customerDto.getCustEmail();
		Customer cust  = custRepo.findByCustEmail(email);
		if(cust == null) {
			
			Customer entity = CustomerMapper.convertToEntity(customerDto);
			custRepo.save(entity); // I need to save the record First,as insideEmail Service I am using d Record. If I dont...N.P.Exception will come
			eServ.sendEmailWithRandomPassword(email);
			
			return customerDto;
		}else {
			return null;
		}
		
	}

	@Override
	public AuthResponse login(CustomerDto customerDto) {
		AuthResponse response = null;
		
		UsernamePasswordAuthenticationToken authToken = 
					new UsernamePasswordAuthenticationToken(customerDto.getCustEmail(),customerDto.getPwd());
		Authentication authenticate = authManager.authenticate(authToken);
		if(authenticate.isAuthenticated()) {
			response = new AuthResponse();
			Customer cust = custRepo.findByCustEmail(customerDto.getCustEmail());
			CustomerDto cDto = CustomerMapper.convertToDto(cust);
			response.setCustomer(cDto);// we can skip above 2 line also
			response.setToken("");
			return response;
		}
		return response;
		
		
	}

	@Override
	public Boolean resetPassword(ResetPasswordDto resetPwdDto) {
		
		Customer customer = custRepo.findByCustEmail(resetPwdDto.getCustEmail());
		if(customer != null) {
		String encodedPwd= pwdEncoder.encode(resetPwdDto.getNewPassword());
		customer.setPwd(encodedPwd);
		customer.setPwdUpdated("yes");
		custRepo.save(customer);
		
		return true;
	}else {
		return false;
	}
	}

	@Override
	public CustomerDto getCustomerData(String email) {
	 Customer customer = custRepo.findByCustEmail(email);
	 if(customer != null) {
	return CustomerMapper.convertToDto(customer);
	 }else {
		 return null;
	 }
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer customer = custRepo.findByCustEmail(email);
		
		
		return new User(customer.getCustEmail(),customer.getPwd(),Collections.EMPTY_LIST);
	}

	@Override
	public Boolean forgotPwd(String email) {
		Customer c = custRepo.findByCustEmail(email);
		if( c != null) {
			eServ.sendEmailForgotPwd(email);
			return true;
		}
		return false;
	}

	@Override
	public Boolean isEmailUnique(String Email) {
		Customer customer = custRepo.findByCustEmail(Email);
		if(customer != null) {
			return false;
		}else {
			return true;
		}
	}
	

}
