package in.ashokit.mapper;

import org.modelmapper.ModelMapper;

import in.ashokit.dto.ShippingAddressDto;
import in.ashokit.entity.ShippingAddress;

public class ShippingAddressMapper {
	
	private static final ModelMapper mapper = new ModelMapper();
	
	public static ShippingAddressDto entityToDto(ShippingAddress address) {
		return mapper.map(address,ShippingAddressDto.class);
	}
	
	public static ShippingAddress dtoToEntity (ShippingAddressDto addressDto) {
		return mapper.map(addressDto,ShippingAddress.class);
	}

}
