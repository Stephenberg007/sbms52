package in.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import in.ashokit.entity.Product;

public interface ProductRepo extends JpaRepository<Product,Integer> {
	public List<Product> findByProdCategoryCategoryId(Integer categoryId);
	public List<Product> findByProdCategoryCategoryName(String categoryName);
	public List<Product> findByProdNameContainingIgnoreCase(String prodName);
}
