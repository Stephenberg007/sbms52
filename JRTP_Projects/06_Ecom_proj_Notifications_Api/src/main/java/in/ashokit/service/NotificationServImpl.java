package in.ashokit.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import in.ashokit.dto.WatiParameters;
import in.ashokit.dto.WatiRequest;
import in.ashokit.dto.WatiResponse;
import in.ashokit.entity.Customer;
import in.ashokit.entity.Order;
import in.ashokit.repo.OrderRepo;
import lombok.SneakyThrows;

@Service
public class NotificationServImpl implements NotificationService {
	
	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	EmailService emailServ;
	
	@Value("${wati.api.key}")
	private String watiToken;
	
	@Value("${wati.aman.template}")
	private String templateName;
	
	@Value("${wati.aman.url}")
	private String watiEndPointUrl;
	
	RestTemplate rt = new RestTemplate();
	
	
	
	@Override
	//@Scheduled(cron = " 0 0 7 * * *")
	public Integer sendDeliveryNotifications() {// This method will execute DAILY at 7 AM.
		WatiResponse response = null;
		List<Order> orders = orderRepo.findByDeliveryDate(LocalDate.now());// Ppl Having today as Delivery Date
		for(Order order: orders) {
			Customer customer = order.getCustomer();
			sendEmailNotification(customer.getCustEmail(), order.getOrderTrackingNum());
			 response = sendWatiNotification(customer,order.getOrderTrackingNum());
		}
		
		return orders.size();
	}

	@Override
	public Integer sendNotificationToPendingOrders() {
	List<Order> orders = orderRepo.findByOrderStatus("created");
		return null;
	}
	private boolean sendEmailNotification(String to, String orderTrackingNum) {
		emailServ.sendEmail(to, orderTrackingNum);
		return true;
	}
	
	@SneakyThrows
	private WatiResponse sendWatiNotification(Customer customer,String orderTrackingNum) {
		String apiUrl = watiEndPointUrl+ "?whatsappNumber=91"+customer.getPhNo();
		
		WatiParameters nameParam = new WatiParameters();
		nameParam.setName("name");
		nameParam.setValue(customer.getCustName());
		
//		WatiParameters trackingParam = new WatiParameters();
//		trackingParam.setName("order_Tracking_Number");
//		trackingParam.setValue(orderTrackingNum);
		
		WatiRequest request = new WatiRequest();
		request.setTemplate_name(templateName);
		request.setBroadcast_name("BroadCast");
		//request.setParameters(Arrays.asList(nameParam,trackingParam));
		request.setParameters(Arrays.asList(nameParam));
		// converting the request to json
		
		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = null;
		
		try{
			writeValueAsString = mapper.writeValueAsString(request);
			System.out.println(writeValueAsString);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", watiToken);
		HttpEntity<WatiRequest> reqEntity = new HttpEntity<WatiRequest>(request,headers);
		ResponseEntity<WatiResponse> postForEntity = rt.postForEntity(apiUrl, request, WatiResponse.class);
		
		return postForEntity.getBody();
		
	}
	

}
