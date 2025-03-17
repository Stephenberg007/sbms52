package in.ashokit.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.binding.QuoteDTO;
import in.ashokit.binding.ResetPasswordDTO;
import in.ashokit.binding.UserRegistrationDTO;
import in.ashokit.constants.AppConstants;
import in.ashokit.controller.UserController;
import in.ashokit.service.EmailService;
import in.ashokit.service.UserServImpl;

@WebMvcTest(value = UserController.class)
public class UserControllerTest {

	@MockitoBean
	UserServImpl userImpl;

	@MockitoBean
	EmailService emailServ;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void registerUserTest_UniqueEmail() throws Exception {
		// Prepairing the requirements
		UserRegistrationDTO userRegDto = new UserRegistrationDTO();
		String email = "krmauryaaman@gmail.com";
		userRegDto.setUserEmail(email);

		Map<Integer, String> countriesMap = new HashMap<>();
		countriesMap.put(1, "India");

		when(userImpl.uniqueEmailCheck(email)).thenReturn(true);

		when(userImpl.registerUser(any(UserRegistrationDTO.class))).thenReturn(true);

		when(userImpl.getCountries()).thenReturn(countriesMap);

		doNothing().when(emailServ).sendEmailWithRandomPassword(email);// because here 'Return Type' is void.

		// we need to send Form Data NOT a JSON OBJECT

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration").param("userEmail", email)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED);
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(200, response.getStatus()); // Spring MVC returns status 200 for view rendering
		assertEquals("RegisPag", result.getModelAndView().getViewName());
		assertEquals("Registration Done.Please Login...", result.getModelAndView().getModel().get("sMsg"));

	}

	@Test
	public void registerUserTest_NOUniqueEmail() throws Exception {
		// Prepairing the requirements
		UserRegistrationDTO userRegDto = new UserRegistrationDTO();
		String email = "krmauryaaman@gmail.com";
		userRegDto.setUserEmail(email);

		Map<Integer, String> countriesMap = new HashMap<>();
		countriesMap.put(1, "India");

		when(userImpl.uniqueEmailCheck(email)).thenReturn(false);
		// userImpl.registerUser(userRegDto);
		// emailServ.sendEmailWithRandomPassword(email); We Do not need them Now
		when(userImpl.getCountries()).thenReturn(countriesMap);

		ObjectMapper mapper = new ObjectMapper();
		String userJson = mapper.writeValueAsString(userRegDto);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/registration").param("userEmail", email)
				.contentType(MediaType.APPLICATION_FORM_URLENCODED);
		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(200, response.getStatus()); // Spring MVC returns status 200 for view rendering
		assertEquals("RegisPag", result.getModelAndView().getViewName());
		assertEquals("Email already Registered.Please Login..",
				result.getModelAndView().getModel().get(AppConstants.ERR_MSG));

	}

	@GetMapping("/")
	public String loginPage(Model model) {
		UserRegistrationDTO userDto = new UserRegistrationDTO();
		model.addAttribute("userRegObj", userDto);
		return AppConstants.LOGIN_PAGE;
	}

	@Test
	public void loginPagetest() throws Exception {
		UserRegistrationDTO userDto = new UserRegistrationDTO();

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals("LoginPage", result.getModelAndView().getViewName());

	}

	@Test
	public void userLogintest_OnlyUserPresent() throws Exception {
		UserRegistrationDTO user = new UserRegistrationDTO();
		String email = "Amanuj98@gmail.com";
		String pwd = "aassddff";
		String pwdUpdated = "No";
		user.setUserEmail(email);
		user.setUserPwd(pwd);
		user.setPwdUpdated(pwdUpdated);
		when(userImpl.login(email, pwd)).thenReturn(user);
		
		//ResetPasswordDTO resetPwd = new ResetPasswordDTO();
		//resetPwd.setEmail(email);// Spring created it by itself During UNit Testing

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/login").param("userEmail", email)
																					.param("userPwd", pwd)
																					.param("pwdUpdated", pwdUpdated)
																					.contentType(MediaType.APPLICATION_FORM_URLENCODED);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus()); // Spring MVC returns status 200 for view rendering
		assertEquals("ResetPwd", result.getModelAndView().getViewName());
		

	}
	
	@Test
	public void userLogintest_UserPresentAndPwdUpdated() throws Exception {
		UserRegistrationDTO user = new UserRegistrationDTO();
		String email = "Amanuj98@gmail.com";
		String pwd = "aassddff";
		String pwdUpdated = "Yes";
		user.setUserEmail(email);
		user.setUserPwd(pwd);
		user.setPwdUpdated(pwdUpdated);
		
		QuoteDTO quote = new QuoteDTO();
		quote.setAuthor("Aman");
		quote.setId(1);
		quote.setQuote("J UNIT TESTING");
		
		when(userImpl.login(email, pwd)).thenReturn(user);
		when(userImpl.getQuotation()).thenReturn(quote);
		
		//ResetPasswordDTO resetPwd = new ResetPasswordDTO();
		//resetPwd.setEmail(email);// Spring created it by itself During UNit Testing

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/login").param("userEmail", email)
																					.param("userPwd", pwd)
																					.param("pwdUpdated", pwdUpdated)
																					.contentType(MediaType.APPLICATION_FORM_URLENCODED);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(200, response.getStatus()); // Spring MVC returns status 200 for view rendering
		assertEquals("Dashboard", result.getModelAndView().getViewName());
		

	}

	
	
	@Test
	public void userLogintest_NoUserPresent() throws Exception {
		UserRegistrationDTO user = new UserRegistrationDTO();
		String email = "Amanuj98@gmail.com";
		String pwd = "aassddff";
		user.setUserEmail(email);
		user.setUserPwd(pwd);
		when(userImpl.login(email, pwd)).thenReturn(null);

		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/login").param("userEmail", email)
				.param("userPwd", pwd).contentType(MediaType.APPLICATION_FORM_URLENCODED);

		MvcResult result = mockMvc.perform(request).andReturn();
		MockHttpServletResponse response = result.getResponse();

		assertEquals(200, response.getStatus()); // Spring MVC returns status 200 for view rendering
		assertEquals("LoginPage", result.getModelAndView().getViewName());
		assertEquals("Username or Password is Invalid", result.getModelAndView().getModel().get(AppConstants.ERR_MSG));
	}

}
