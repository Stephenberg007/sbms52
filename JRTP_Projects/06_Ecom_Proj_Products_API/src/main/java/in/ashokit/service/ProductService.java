package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.ProductCategoryDTO;
import in.ashokit.dto.ProductDTO;

public interface ProductService {
	public List<ProductCategoryDTO> getAllCategories();
	public List<ProductDTO> getProductsByCatgId(Integer catgId);
	public List<ProductDTO> getProductsByProdName(String prodName);
	public ProductDTO getProductsByProdId(Integer prodId);
}
