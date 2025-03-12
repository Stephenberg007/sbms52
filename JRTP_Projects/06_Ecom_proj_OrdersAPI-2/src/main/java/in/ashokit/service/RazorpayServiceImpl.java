package in.ashokit.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

import lombok.SneakyThrows;

@Service
public class RazorpayServiceImpl implements RazorpayService{
	
	
	@Value("${razorpay.key.id}")
	private String razorPayKey;
	
	@Value("${razorpay.secret.key}")
	private String razorPaySecret;
	
	private RazorpayClient client;
	
	@Override
	@SneakyThrows
	public Order createPaymentOrder(double amount) {
		JSONObject orderReq= new JSONObject();
		
		orderReq.put("amount", amount * 100);// amount in Razorpay is always in the lowest unit i.e paisa for INR so we converted it into Rupee
		orderReq.put("currency", "INR");
		orderReq.put("payment_capture",1);
		
		
		this.client = new RazorpayClient(razorPayKey, razorPaySecret);// My Razorpay Account's Keys and Secret
		
		Order razorPayOrder = client.orders.create(orderReq);
		
		return razorPayOrder;
	}

}
