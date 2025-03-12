package main.java.in.ashokit.service;

import java.util.List;

import main.java.in.ashokit.dto.ProductCategoryDTO;
import main.java.in.ashokit.dto.ProductDTO;

public interface ProductService {
	public List<ProductCategoryDTO> getAllCategories();
	public List<ProductDTO> getProductsByCatgName(String catgName);
	public List<ProductDTO> getProductsByProdName(String prodName);
	public ProductDTO getProductsByProdId(Integer prodId);
}
