package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import in.ashokit.controller.MsgRestController;
import in.ashokit.service.MsgService;

//@SpringBootTest-- used for testing Normal SpringBoot class

@WebMvcTest(value=MsgRestController.class)
public class MsgRestControllerTest {
	
	//@Autowired-- would have injected A Real Object
	
	@MockitoBean	// A mock object would be Inserted Now
	private MsgService msgService;// if we had used @Autowired then Actual Object wuld hav Inserted
	
	@Autowired
	MockMvc mockMvc;	// will be used to send Get Request to REST API programatically
	
	@Test
	public void getGreetTest() throws Exception{
		when(msgService.getGreetMsg()).thenReturn("Hey Handsome");// defining mock obj ka method behaviour i.e mock Output
								// actual method call hoga toh 'Good Evening' aaega Output
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/greet");
		MvcResult result= mockMvc.perform(reqBuilder).andReturn();// we are sending the request programaticallyashokit
		
		MockHttpServletResponse response = result.getResponse();
		String actual= response.getContentAsString();
		String expected = "hey handsome";
		assertEquals(expected,actual);
		
	}
	
	@Test
	public void getWelcomeTest() throws Exception {
		when(msgService.getWelcomeMsg()).thenReturn("Welcome in Test Case"); // we are mocking the result that would come, bcoz actual msgService wont hit the method in Service Layer
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/welcome");// creating a Mock               GET request for this URL PAttern
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();	// Performing GET Request
		MockHttpServletResponse response = result.getResponse();
		String contentAsString = response.getContentAsString();
		// validating the result obtained
		int actual = response.getStatus();
		int expected=200;
		assertEquals(expected,actual);
		
		// Code By ChatGpt
		
		// mockMvc.perform(get("/welcome"))
        // .andExpect(status().isOk()) // Check for HTTP 200 status
        // .andExpect(content().string("WELCOME IN TEST CASE")); 
		
	}

}
