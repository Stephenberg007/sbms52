package in.ashokit.service;

import java.util.Collections;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.ashokit.entity.UserEntity;
import in.ashokit.repo.UserRepo;

@Service
public class UserService implements UserDetailsService {
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder pwdEncoder;
	
	
	@Autowired
	UserRepo userRepo;
	
	public Boolean saveUser(UserEntity user) {
	//	BCryptPasswordEncoder pwdEncoder= new BCryptPasswordEncoder();
		String encodedPwd= pwdEncoder.encode(user.getPwd());
		user.setPwd(encodedPwd);
		
		UserEntity savedUser = userRepo.save(user);
		return savedUser.getUid()!=null;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // for our logic email is the UserName
		
		UserEntity usr = userRepo.findByEmail(email);
		
		return new User(usr.getEmail(),usr.getPwd(),Collections.EMPTY_LIST);
		
	}
	
}
