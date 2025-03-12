package in.ashokit.response;

import lombok.Builder;
import lombok.Data;

@Data

public class PurchaseOrderResponse {
	
	private String razorpayOrderId;
	private String orderStatus;
	private String orderTrackingNumber;
}
