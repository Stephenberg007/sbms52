package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import in.ashokit.service.MsgService;

@SpringBootTest
public class MsgServiceTest {

	@Autowired
	MsgService msgService;
	
	@Test
	public void getGreetTest() {
		String actualMsg =msgService.getMsg();
		String expectedMsg = "Getting Greet Message";
		
		assertEquals(expectedMsg,actualMsg);
		
	}
	@Test
	public void getNewMsgTestUsingReflection() throws Exception {
		//String actualMsg= msgService.getNewMsg();
		
		MsgService msgServ= new MsgService();
		 Method method = MsgService.class.getDeclaredMethod("getNewMsg");
	        method.setAccessible(true); // Bypass private access
	        String result = (String) method.invoke(msgServ);// it means msgServ.getNewMsg()
	        assertEquals("Testing Private Method", result);
		
	}
	
	@Test
	public void getNewMsgTestUsingReflectn() throws Exception {
		//String actualMsg= msgService.getNewMsg();
		
		MsgService msgServ= new MsgService();
		 Method method = MsgService.class.getDeclaredMethod("getNewMsg",String.class);
	        method.setAccessible(true); // Bypass private access
	        String result = (String) method.invoke(msgServ,"Overloading");// it means msgServ.getNewMsg("Overloading");
	        assertEquals("Second Test Overloading", result);
		
	}
}
