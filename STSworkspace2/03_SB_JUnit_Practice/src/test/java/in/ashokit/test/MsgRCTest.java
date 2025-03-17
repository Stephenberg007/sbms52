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

@WebMvcTest(value=MsgRestController.class)
public class MsgRCTest {
	
	@MockitoBean
	private MsgService msgService;
	
	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void getMsgTest() throws Exception {
		when(msgService.getMsg()).thenReturn("Hi Aman");
		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.get("/greet");
				MvcResult result = mockMvc.perform(reqBuilder).andReturn();
				MockHttpServletResponse response = result.getResponse();
				
				String actualResult = response.getContentAsString();
				String expectedResult = "HI AMAN";
				assertEquals(expectedResult,actualResult);
				
	}

}
