package in.ashokit.service;

import in.ashokit.dto.ShippingAddressDto;
import in.ashokit.entity.ShippingAddress;

public interface ShippingAddrService {
	public boolean saveAddress(ShippingAddressDto addrDto);;
	
}
