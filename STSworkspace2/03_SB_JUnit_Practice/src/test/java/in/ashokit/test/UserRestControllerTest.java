package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.ArgumentMatchers.any;
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

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.controller.UserRestController;
import in.ashokit.dto.UserDto;
import in.ashokit.service.UserService;

@WebMvcTest(UserRestController.class)
public class UserRestControllerTest {
	
	@MockitoBean
	private UserService userServ;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void saveUserTest1() throws Exception {
		UserDto user = new UserDto();
		user.setuId(1);
		user.setuCity("Dehradun");
		user.setuName("Aman Maurya");
		
		when(userServ.saveUser(any(UserDto.class))).thenReturn(true);
		ObjectMapper objectMapper = new ObjectMapper();
		String userJson = objectMapper.writeValueAsString(user);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/save").content(userJson)
				.contentType("application/json");
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int actualStatus = response.getStatus();
		int expectedStatus = 201;
		assertEquals(expectedStatus, actualStatus);
		
		
	}
	
	@Test
	public void saveUserTest2() throws Exception {
		UserDto user = new UserDto();
		user.setuId(1);
		user.setuCity("Dehradun");
		user.setuName("Aman Maurya");
		
		when(userServ.saveUser(any(UserDto.class))).thenReturn(false);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String userJson = objectMapper.writeValueAsString(user);
		
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/save").content(userJson)
				.contentType("application/json");
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		int actualStatus = response.getStatus();
		int expectedStatus = 500;
		assertEquals(expectedStatus, actualStatus);
		
		
	}

}
