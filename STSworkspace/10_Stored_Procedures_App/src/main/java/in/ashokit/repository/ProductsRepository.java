package in.ashokit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import in.ashokit.entity.Products;

public interface ProductsRepository extends JpaRepository<Products,Integer> {
	
	@Query(value="call getProducts()", nativeQuery=true)
	public List<Products> getAllProducts();	
	
}
