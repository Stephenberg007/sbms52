package in.ashokit.service;

import java.util.List;

import in.ashokit.dto.ProductDTO;

public interface ProductService {
	public List<ProductDTO> getProductsByCatgId(Integer categoryId);
	public List<ProductDTO> getProductsByCatgName(String catgName);
	public List<ProductDTO> getProductsByProdName(String prodName);
	public ProductDTO getProductsByProdId(Integer prodId);
}
