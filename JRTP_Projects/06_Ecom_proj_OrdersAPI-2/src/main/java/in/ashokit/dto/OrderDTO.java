package in.ashokit.dto;

import java.time.LocalDateTime;
import java.util.List;

public class OrderDTO {
	private Integer orderId;
	private String OrderTrackingNum;
	private Integer totalQuantity;
	private Long totalPrice;
	private String orderStatus;
	
	private LocalDateTime dateCreated;
	private LocalDateTime deliveryDate;
	
	private String paymentStatus;
	private String razorPayOrderId;
	private String razorPayPaymentId;
	private String invoiceUrl;
	
	private Integer customerId;
	private Integer addressId;
	
	private List<OrderItemDto> orderItems;
	
	
}
