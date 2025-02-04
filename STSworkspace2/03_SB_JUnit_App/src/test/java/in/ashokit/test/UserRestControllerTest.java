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
import in.ashokit.model.User;
import in.ashokit.service.UserService;

@WebMvcTest(value = UserRestController.class)
public class UserRestControllerTest {

	@MockitoBean
	private UserService userServ;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void saveUserTest_c1() throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("Aman");
		when(userServ.saveUser(any())).thenReturn(true);//any() means Object of any type
		ObjectMapper objectMapper = new ObjectMapper();
		String userJson = objectMapper.writeValueAsString(user);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/save").content(userJson) // Set JSON
																											// content
				.contentType("application/json");
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String content = response.getContentAsString();
		String expectedContent = "User Saved";
		// validating the result obtained
		int actual = response.getStatus();
		int expected = 201;
		assertEquals(expected, actual);
		assertEquals(expectedContent, content);

	}
	
	@Test
	public void saveUserTest_c2() throws Exception {
		User user = new User();
		user.setId(1);
		user.setName("Aman");
		when(userServ.saveUser(any())).thenReturn(false);//just defined behaviour of Mock Object
		ObjectMapper objectMapper = new ObjectMapper();
		String userJson = objectMapper.writeValueAsString(user);

		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/save").content(userJson) // Set JSON
																											// content
				.contentType("application/json");
		MvcResult result = mockMvc.perform(reqBuilder).andReturn();// Its here that we sent request to RestController Class
		MockHttpServletResponse response = result.getResponse();
		String content = response.getContentAsString();
		String expectedContent = "Not Saved";
		// validating the result obtained
		int actual = response.getStatus();
		int expected = 500;
		assertEquals(expected, actual);
		assertEquals(expectedContent, content);

	}


}
