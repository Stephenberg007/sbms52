package in.ashokit.service;

import java.util.Map;


import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

import in.ashokit.dto.StudentOrderDto;
import in.ashokit.entity.StudentOrder;
import in.ashokit.repo.StudentOrderRepo;

@Service
public class StudentService {
	
	@Autowired
	private StudentOrderRepo stuRepo;
	
	@Value("${razorpay.key.id}")
	private String razorPayKey;
	
	@Value("${razorpay.secret.key}")
	private String razorPaySecret;
	
	private RazorpayClient client;
	
	public StudentOrder createOrder(StudentOrderDto orderDto) throws RazorpayException {
		JSONObject orderReq= new JSONObject();
		
		orderReq.put("amount", orderDto.getAmount() * 100);
		orderReq.put("currency", "INR");
		orderReq.put("receipt", orderDto.getEmail() );
		
		this.client = new RazorpayClient(razorPayKey, razorPaySecret);// I or My Business is the RazorPay's CLIENT
		
		// Create Order In RazorPay	
		Order razorPayOrder = client.orders.create(orderReq);// I am providing my created JSON as the argument to this method
		System.out.println(razorPayOrder);
		
		StudentOrder order = new StudentOrder();
	
		BeanUtils.copyProperties(orderDto, order);
		
		order.setRazorpayOrderId(razorPayOrder.get("id"));
		order.setOrderStatus(razorPayOrder.get("status"));// these both fields are available with our Razorpay-PreDefined Class i.e Order
		
		
		stuRepo.save(order);
		return order;
		
		
	}
	
	public StudentOrder updateOrder(Map<String,String> responsePayLoad) {
		
		String razorPayOrderId = responsePayLoad.get("razorpay_order_id");
		StudentOrder order = stuRepo.findByRazorpayOrderId(razorPayOrderId);
		order.setOrderStatus("PAYMENT_COMPLETED");
		StudentOrder updatedOrder = stuRepo.save(order);// Updating the status in DB
		// we can write logic to send email to customer Here
		return updatedOrder;
	}
	
}
