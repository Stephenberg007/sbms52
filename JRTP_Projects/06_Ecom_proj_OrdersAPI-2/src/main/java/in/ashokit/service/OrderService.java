package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.OrderDto;
import in.ashokit.dto.OrderItemDto;
import in.ashokit.dto.PaymentCallBackDto;
import in.ashokit.response.PurchaseOrderRequest;
import in.ashokit.response.PurchaseOrderResponse;

public interface OrderService {
	public PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest);
	
	public PurchaseOrderResponse updateOrder(PaymentCallBackDto paymentCallBackDto);
	
	public List<OrderDto> getOrdersByEmail(String email);
	
	public List<OrderItemDto> getOrderItemsById(Integer orderId);
	
	
	

}
