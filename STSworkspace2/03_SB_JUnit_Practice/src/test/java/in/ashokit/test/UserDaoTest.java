package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import in.ashokit.dao.UserDao;
import in.ashokit.dto.UserDto;

@ExtendWith(MockitoExtension.class)
public class UserDaoTest {
	
	@InjectMocks
	private UserDao userDao;
	
	@Test
	public void saveUserTest() {
		UserDto user = new UserDto();
			user.setuCity("aa");
			user.setuId(1);
			user.setuName("Aman");
			boolean actual = userDao.saveUser(user);
			boolean expected = true;
			assertEquals(expected,actual);
			
	}

}
