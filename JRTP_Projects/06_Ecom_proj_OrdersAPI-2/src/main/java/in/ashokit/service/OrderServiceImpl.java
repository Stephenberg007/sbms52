package in.ashokit.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.OrderDto;
import in.ashokit.dto.OrderItemDto;
import in.ashokit.dto.PaymentCallBackDto;
import in.ashokit.dto.ShippingAddressDto;
import in.ashokit.entity.Customer;
import in.ashokit.entity.Order;
import in.ashokit.entity.OrderItem;
import in.ashokit.entity.ShippingAddress;
import in.ashokit.mapper.OrderMapper;
import in.ashokit.mapper.ShippingAddressMapper;
import in.ashokit.repo.CustomerRepo;
import in.ashokit.repo.OrderItemRepo;
import in.ashokit.repo.OrderRepo;
import in.ashokit.repo.ShippingAddressRepo;
import in.ashokit.response.PurchaseOrderRequest;
import in.ashokit.response.PurchaseOrderResponse;

@Service
public class OrderServiceImpl implements OrderService {
	
	public final OrderRepo orderRepo;
	public final CustomerRepo custRepo;
	public final OrderItemRepo itemRepo;
	public final ShippingAddressRepo addrRepo;
	public final RazorpayService razorpayService ;
	
	
	@Autowired
	private EmailService emailServ;
	
	
	@Autowired
	public OrderServiceImpl(OrderRepo orderRepo,CustomerRepo custRepo,OrderItemRepo itemRepo,
			ShippingAddressRepo addrRepo, RazorpayService razorpayService) {
		this.orderRepo=orderRepo;
		this.custRepo=custRepo;
		this.itemRepo=itemRepo;
		this.addrRepo=addrRepo;
		this.razorpayService =  razorpayService;
		
	}

	@Override
	public PurchaseOrderResponse createOrder(PurchaseOrderRequest orderRequest) {
		CustomerDto customerDto = orderRequest.getCustomer();
		ShippingAddressDto addressDto = orderRequest.getAddress();
		OrderDto orderDto = orderRequest.getOrder();
		List<OrderItemDto> orderItemsDto = orderRequest.getOrderItems();
		
		// Handling Customer
		Customer c = custRepo.findByCustEmail(customerDto.getEmail());
		if(c==null) {
			// Do Feign Client Communication with Customer API to Register the customer
				c= new Customer();
				c.setCustName(customerDto.getName());
				c.setCustEmail(customerDto.getEmail());
				c.setPhNo(customerDto.getPhoneNo());
				custRepo.save(c);			
			
		}
		// Save Address
		
		ShippingAddress address = ShippingAddressMapper.dtoToEntity(addressDto);
		address.setCustomer(c);
		addrRepo.save(address);
		
		// save Order in DB
		
		Order order = OrderMapper.dtoToEntity(orderDto);
		String orderTrackingNum = generateOrderTrackingNum();
		order.setOrderTrackingNum(orderTrackingNum);
		
		com.razorpay.Order paymentOrder = razorpayService.createPaymentOrder(orderDto.getTotalPrice());
		String orderId = paymentOrder.get("id");	// we are getting this automatically from razorpay's Side on invoking create()
		String status = paymentOrder.get("status");// these two are fields returned by Order type object of Razorpay's Predefined Order class
		order.setRazorpayOrderId(orderId);
		order.setOrderStatus(status);
		
		order.setAddress(address);//Association Mapping
		order.setCustomer(c);// Association Mapping
		Order savedOrder = orderRepo.save(order);
		
		//saving Order Items in Db and associating OrderId with it
		 List<OrderItem> orderItems = orderItemsDto.stream().map(itemDto -> {
	            OrderItem orderItem = new OrderItem();
	           orderItem.setImageUrl(itemDto.getImageUrl());
	           orderItem.setQuantity(itemDto.getQuantity());
	           orderItem.setProdName(itemDto.getProductname());
	           orderItem.setUnitPrice(itemDto.getUnitPrice());
	           orderItem.setOrderItemId(itemDto.getOrderItemId());
	           orderItem.setOrder(savedOrder); // Association Mapping
	            return orderItem;
	        }).collect(Collectors.toList());

	        itemRepo.saveAll(orderItems);
		
		// preparing response
	        PurchaseOrderResponse response = new PurchaseOrderResponse();
	        response.setOrderStatus(status);
	        response.setOrderTrackingNumber(orderTrackingNum);
	        response.setRazorpayOrderId(orderId);
		return response;		// for creating this response we used Setter methods.
								// In the markest People are using Builder Design pattern Also. We can use it too
	}
	
	private String generateOrderTrackingNum() {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmm");
		String timeStamp = sdf.format(new Date());//date of order in format provided by us
		String randomUuid = UUID.randomUUID().toString().substring(0,5).toUpperCase();
		
		return "OD" + timeStamp + randomUuid;
		 
	}
	
	// this method will be invoked once the payment for the order is completed at Ui side
	@Override
	public PurchaseOrderResponse updateOrder(PaymentCallBackDto paymentCallBackDto) {
		Order order = orderRepo.findByRazorpayOrderId(paymentCallBackDto.getRazorpayOrderId());
		if(order!=null) {
			order.setOrderStatus("Payment Confirmed");
			order.setDeliveryDate(LocalDate.now().plusDays(3));
			order.setRazorpayPaymentId(paymentCallBackDto.getRazorpayPaymentId());
			orderRepo.save(order);
			// Sending Email after payment is Done and Order status Updated as confirmed
			emailServ.sendEmail(order.getEmail(), order.getOrderTrackingNum());
			
		}
		PurchaseOrderResponse response = new PurchaseOrderResponse();
        response.setOrderStatus(order.getOrderStatus());
        response.setOrderTrackingNumber(order.getOrderTrackingNum());
        response.setRazorpayOrderId(paymentCallBackDto.getRazorpayOrderId());
	return response;
		
		
	}

	@Override
	public List<OrderDto> getOrdersByEmail(String email) {
		List<Order> ordersList = orderRepo.findByEmail(email);
		List<OrderDto> ordersDtoList = ordersList.stream()
													.map(entityElement ->{
													OrderDto orderDto = new OrderDto();
													BeanUtils.copyProperties(entityElement, orderDto);
													return orderDto;	
													}).collect(Collectors.toList());
				
		
		return ordersDtoList;
	}

	@Override
	public List<OrderItemDto> getOrderItemsById(Integer orderId) {
		List<OrderItem> orderItems = itemRepo.findByOrderOrderId(orderId);
		List<OrderItemDto> orderItemList = orderItems.stream().map(entityElement -> {
			OrderItemDto orderItemDto = new OrderItemDto();
			BeanUtils.copyProperties(entityElement, orderItemDto);
			return orderItemDto;
		}).collect(Collectors.toList());
		
		return orderItemList;

	}

}
