package in.ashokit.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.razorpay.RazorpayException;

import in.ashokit.dto.StudentOrderDto;
import in.ashokit.entity.StudentOrder;
import in.ashokit.service.StudentService;

@Controller
public class StudentController {
	@Autowired
	private StudentService stuServ;
	
	@GetMapping("/")
	public String getOderDetails(Model model) {
		StudentOrderDto order = new StudentOrderDto();
		model.addAttribute("order", order);
		return "index";
	}
	
	@PostMapping(value="/purchase", produces="application/json")
	@ResponseBody
	public ResponseEntity<StudentOrder> createOrder(@RequestBody StudentOrderDto orderDto) throws RazorpayException {
		StudentOrder order = stuServ.createOrder(orderDto);
		return new ResponseEntity<>(order,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/payment-callback")
	public String handlePaymentCallback(@RequestParam Map<String,String> respPayload) {
		System.out.println(respPayload);
		stuServ.updateOrder(respPayload);
		return "Success";// redirection to this page
		
	}
	
}
