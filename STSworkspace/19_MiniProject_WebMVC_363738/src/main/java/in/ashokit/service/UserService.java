package in.ashokit.service;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import in.ashokit.entity.User;

public interface UserService {
	public boolean saveUser(User user);
	public List<User> getAllUsers();
	public User getUserById(Integer uid);
	public boolean deleteUserById(Integer uid);
	
	
	

}
