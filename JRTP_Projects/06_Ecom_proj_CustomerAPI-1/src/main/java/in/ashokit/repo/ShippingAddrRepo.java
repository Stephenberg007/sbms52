package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.dto.ShippingAddressDto;
import in.ashokit.entity.ShippingAddress;

public interface ShippingAddrRepo extends JpaRepository<ShippingAddress,String> {
	
}
