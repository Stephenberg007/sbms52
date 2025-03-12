package in.ashokit.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import in.ashokit.dto.CustomerDto;
import in.ashokit.entity.Customer;

@Component
public class CustomerMapper {
private static final ModelMapper mapper = new ModelMapper();
	
	public static CustomerDto convertToDto(Customer entity) {
		return mapper.map(entity, CustomerDto.class);
	}
	
	public static Customer convertToEntity(CustomerDto dto) {
		return mapper.map(dto, Customer.class);
	}

}
