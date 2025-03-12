package in.ashokit.mapper;

import org.modelmapper.ModelMapper;

import in.ashokit.dto.OrderDto;
import in.ashokit.entity.Order;

public class OrderMapper {
	private static final ModelMapper mapper = new ModelMapper();
	
	public static OrderDto entityToDto(Order order) {
		return mapper.map(order,OrderDto.class);
	}
	
	public static Order dtoToEntity(OrderDto orderDto) {
		return mapper.map(orderDto,Order.class);
	}

}
