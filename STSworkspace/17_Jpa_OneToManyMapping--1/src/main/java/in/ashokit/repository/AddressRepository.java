package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Address;

public interface AddressRepository extends JpaRepository<Address,Integer>{
	@Query("FROM Address where city=:city")
	public List<Address> findBycity(String city);

}
