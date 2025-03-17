package in.ashokit.dao;

import org.springframework.stereotype.Repository;

import in.ashokit.dto.UserDto;

@Repository
public class UserDao {
public boolean saveUser(UserDto user) {
	// db Logic
	return true;
}
	
}