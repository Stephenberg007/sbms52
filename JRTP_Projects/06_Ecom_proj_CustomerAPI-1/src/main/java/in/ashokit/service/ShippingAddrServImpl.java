package in.ashokit.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import in.ashokit.dto.ShippingAddressDto;
import in.ashokit.entity.ShippingAddress;
import in.ashokit.repo.ShippingAddrRepo;

public class ShippingAddrServImpl implements ShippingAddrService {

	private final ShippingAddrRepo addrRepo;
	
	@Autowired
	public ShippingAddrServImpl (ShippingAddrRepo addrRepo) {
		this.addrRepo=addrRepo;
	}
	
	
	@Override
	public boolean saveAddress(ShippingAddressDto addrDto) {
		ShippingAddress addr = new ShippingAddress();
		BeanUtils.copyProperties(addrDto, addr);
		ShippingAddress savedAddr = addrRepo.save(addr);
		return savedAddr.getAddrId()!=null;
	}

}
