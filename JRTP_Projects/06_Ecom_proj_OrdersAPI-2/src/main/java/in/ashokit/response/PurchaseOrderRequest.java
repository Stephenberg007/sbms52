package in.ashokit.response;

import java.util.List;

import in.ashokit.dto.CustomerDto;
import in.ashokit.dto.OrderDto;
import in.ashokit.dto.OrderItemDto;
import in.ashokit.dto.ShippingAddressDto;
import lombok.Data;

@Data
public class PurchaseOrderRequest {
	
	private CustomerDto customer;
	private ShippingAddressDto address;
	private OrderDto order;
	private List<OrderItemDto> orderItems;
	
}
