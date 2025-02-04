package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dao.UserDao;
import in.ashokit.model.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	
	
	public boolean saveUser(User user) {
		boolean saveUser = userDao.saveUser(user);// I need to mock this line
		return saveUser ;
	}

}
