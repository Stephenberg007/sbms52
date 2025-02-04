package in.ashokit.test;

import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import in.ashokit.dao.UserDao;
import in.ashokit.model.User;
import in.ashokit.service.UserService;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
	//@MockitoBean
	@Mock
	private UserDao userDao;	
	
	@InjectMocks
	private UserService userServ;
	//@Autowired
	//private UserService userServ;
	
	@Test
	public void saveUserTest() {
		User user = new User();
		user.setId(1);
		user.setName("Aman");
		
		when(userDao.saveUser(user)).thenReturn(true);
		boolean actual= userServ.saveUser(user);
		boolean expected=true;
		Assertions.assertEquals(expected,actual);
				
		
		
	}
	
}
