package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import in.ashokit.dao.UserDao;
import in.ashokit.dto.UserDto;
import in.ashokit.service.UserService;

@SpringBootTest
public class UserServiceTest {
	@MockitoBean
	private UserDao userDao;
	
	@Autowired
	UserService userServ;
	
	@Test
	public void saveUserTest() {
		UserDto user = new UserDto();
		user.setuCity("Doon");
		user.setuId(1);
		user.setuName("Aman");
		when(userDao.saveUser(any(UserDto.class))).thenReturn(true);
		// when(userDao.saveUser(user)).thenReturn(true);....Here this will also work as 
																// we are not converting the object to JSON
		boolean actual = userServ.saveUser(user);
		boolean expected = true;
		assertEquals(expected,actual);
		
	}

}
