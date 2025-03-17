package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.ashokit.dto.OrderDto;
import in.ashokit.dto.OrderItemDto;
import in.ashokit.dto.PaymentCallBackDto;
import in.ashokit.response.ApiResponse;
import in.ashokit.response.PurchaseOrderRequest;
import in.ashokit.response.PurchaseOrderResponse;
import in.ashokit.service.OrderServiceImpl;

@RestController
@CrossOrigin
public class OrderController {
	
	@Autowired
	private OrderServiceImpl orderServ;
	
	
	@PostMapping("/place-order")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> createOrder(@RequestBody PurchaseOrderRequest request){
		PurchaseOrderResponse order = orderServ.createOrder(request);
		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();
		if(order != null) {
		response.setStatus(201);
		response.setMessage("Order Created");
		response.setData(order);
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			response.setStatus(500);
			response.setMessage("Order Creation UnsuccessFul");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping("/payment-callback")
	public ResponseEntity<ApiResponse<PurchaseOrderResponse>> updateOrder(@RequestBody PaymentCallBackDto callbackDto){
		PurchaseOrderResponse updateOrder = orderServ.updateOrder(callbackDto);
		ApiResponse<PurchaseOrderResponse> response = new ApiResponse<>();
		if(updateOrder != null) {
		response.setStatus(201);
		response.setMessage("Order Updated");
		response.setData(updateOrder);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
		}else {
			response.setStatus(500);
			response.setMessage("Order Updation UnsuccessFul");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/orders/{email}")
	public ResponseEntity<ApiResponse<List<OrderDto>>> getOrders(@PathVariable String email){
		List<OrderDto> orders = orderServ.getOrdersByEmail(email);
		ApiResponse<List<OrderDto>> response = new ApiResponse<>();
		if(!orders.isEmpty()) {
		
		response.setStatus(200);
		response.setMessage("Order Updated");
		response.setData(orders);
		return new ResponseEntity<>(response,HttpStatus.OK);
		
	}else {
		response.setStatus(500);
		response.setMessage("No Records Found");
		response.setData(null);
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	}
		
		@GetMapping("/order-items/{orderId}")
		public ResponseEntity<ApiResponse<List<OrderItemDto>>> getOrderItems(@PathVariable Integer orderId){
			List<OrderItemDto> orders = orderServ.getOrderItemsById(orderId);
			ApiResponse<List<OrderItemDto>> response = new ApiResponse<>();
			if(!orders.isEmpty()) {
			
			response.setStatus(200);
			response.setMessage("Order-Items List Generated");
			response.setData(orders);
			return new ResponseEntity<>(response,HttpStatus.OK);
			
		}else {
			response.setStatus(500);
			response.setMessage("No Records Found");
			response.setData(null);
			return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
			
		}
}
}
