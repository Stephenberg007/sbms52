package in.ashokit.dto;

import lombok.Data;

@Data
public class PaymentCallBackDto {
	private String razorpayOrderId;
	private String razorpayPaymentId;
	private String razorpaySignature;
	
}
