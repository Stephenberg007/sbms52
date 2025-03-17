package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import in.ashokit.dao.UserDao;
import in.ashokit.model.User;

@ExtendWith(MockitoExtension.class)
public class UserDaoTest {
	@InjectMocks
	private UserDao userDao;
	
	@Test
	public void saveUserTest() {
		User user = new User();
		user.setId(1);
		user.setName("Aman");
		boolean actual=userDao.saveUser(user);
		boolean expected = true;
		assertEquals(expected,actual);
	}
	
}
