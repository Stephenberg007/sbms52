package in.ashokit.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class OrderDto {
	
	private String OrderTrackingNum;
	private Integer totalQuantity;
	private Double totalPrice;
	private String orderStatus;
	private String email;
	
	private LocalDateTime dateCreated;
	private LocalDate deliveryDate;
	
	
	
	private String paymentStatus;
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String invoiceUrl;
	
	
	
	
}
