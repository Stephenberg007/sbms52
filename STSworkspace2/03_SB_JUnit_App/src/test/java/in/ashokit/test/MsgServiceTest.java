package in.ashokit.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.ashokit.service.MsgService;

@SpringBootTest
public class MsgServiceTest {
	
	@Autowired
	MsgService msgService;
	
	@Test
	public void getWelcomeTest() {
		//MsgService msgService= new MsgService();// Done specifically to avoid using @SpringBootTest
		String actualMsg = msgService.getWelcomeMsg();
		
		String expMsg = "Welcome to AMAN FURNITURE";
		Assertions.assertEquals(actualMsg,expMsg);
	}
	
	@Test
	public void getGreetTest() {
		//MsgService msgService= new MsgService();
		String actualMsg = msgService.getGreetMsg();
		
		String expMsg = "Good Evening";
		Assertions.assertEquals(actualMsg,expMsg);
	}

}
