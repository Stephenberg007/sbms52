package in.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.ShippingAddress;

public interface ShippingAddressRepo extends JpaRepository<ShippingAddress,Integer> {

}
