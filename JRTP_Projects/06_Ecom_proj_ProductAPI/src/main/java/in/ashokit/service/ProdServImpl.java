package main.java.in.ashokit.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.in.ashokit.dto.ProductCategoryDTO;
import main.java.in.ashokit.dto.ProductDTO;
import main.java.in.ashokit.entity.Product;
import main.java.in.ashokit.entity.ProductCategory;
import main.java.in.ashokit.repo.ProdCatgRepo;
import main.java.in.ashokit.repo.ProductRepo;

@Service
public class ProdServImpl implements ProductService{
	//@Autowired
	//ProductRepo prodRepo;
	
	private final ProductRepo prodRepo;
	private final ProdCatgRepo catgRepo;
	
	@Autowired
	public ProdServImpl(ProductRepo prodRepo,ProdCatgRepo catgRepo) {
		this.prodRepo=prodRepo;
		this.catgRepo=catgRepo;
	}
	
	
	@Override
	public List<ProductCategoryDTO> getAllCategories() {
		     return catgRepo.findAll()
		    		 		.stream()
		    		 		.map(ProductCategoryMapper::convertToDto)
		    		 		.collect(Collectors.toList());
		    		 
	}

	@Override
	public List<ProductDTO> getProductsByProdName(String prodName) {
		List<Product> products = prodRepo.findByProdNameContainingIgnoreCase(prodName);// Works similar to LIKE query of SQL
		 List<ProductDTO> allProdDtos = products.stream()
					.map(prod -> {
					ProductDTO pDto = new ProductDTO();
						BeanUtils.copyProperties(prod, pDto);
							return pDto;
						})
						.collect(Collectors.toList());
		return allProdDtos;
	}

	@Override
	public ProductDTO getProductsByProdId(Integer prodId) {
		Optional<Product> byId = prodRepo.findById(prodId);
		if(byId.isPresent()) {
			Product product = byId.get();
			ProductDTO pDto = new ProductDTO();
			BeanUtils.copyProperties(product, pDto);
			return pDto;
		}
		else {
			throw new NoSuchElementException("No Products Found");
		}
	}


	@Override
	public List<ProductDTO> getProductsByCatgName(String catgName) {
		List<Product> products = prodRepo.findByProdCategoryCategoryName(catgName);
		 List<ProductDTO> allProdDtos = products.stream()
					.map(prod -> {
					ProductDTO pDto = new ProductDTO();
						BeanUtils.copyProperties(prod, pDto);
							return pDto;
						})
						.collect(Collectors.toList());
		return allProdDtos;
	
	}

}
