package in.ashokit.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.dto.ProductCategoryDTO;
import in.ashokit.dto.ProductDTO;
import in.ashokit.entity.Product;
import in.ashokit.mapper.ProductCategoryMapper;
import in.ashokit.mapper.ProductMapper;
import in.ashokit.repo.ProdCatgRepo;
import in.ashokit.repo.ProductRepo;

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
		/*List<Product> products = prodRepo.findByProdNameContainingIgnoreCase(prodName);// Works similar to LIKE query of SQL
		 List<ProductDTO> allProdDtos = products.stream()
					.map(prod -> {
					ProductDTO pDto = new ProductDTO();
						BeanUtils.copyProperties(prod, pDto);
							return pDto;
						})
						.collect(Collectors.toList());
		return allProdDtos;*/
		return prodRepo.findByProdNameContainingIgnoreCase(prodName)
						.stream()
						.map(ProductMapper::convertToDto)
						.collect(Collectors.toList());
						
		
	}

	@Override
	public ProductDTO getProductsByProdId(Integer prodId) {
		/*Optional<Product> byId = prodRepo.findById(prodId);
		if(byId.isPresent()) {
			Product product = byId.get();
			ProductDTO pDto = new ProductDTO();
			BeanUtils.copyProperties(product, pDto);
			return pDto;
		}
		else {
			throw new NoSuchElementException("No Products Found");
		}*/
		return prodRepo.findById(prodId)
                .map(ProductMapper::convertToDto)
                .orElseThrow(() -> new NoSuchElementException("No Product Found with ID: " + prodId));
	}


	@Override
	public List<ProductDTO> getProductsByCatgId(Integer catgId) {
		/*List<Product> products = prodRepo.findByProdCategoryCategoryName(catgName);
		 List<ProductDTO> allProdDtos = products.stream()
					.map(prod -> {
					ProductDTO pDto = new ProductDTO();
						BeanUtils.copyProperties(prod, pDto);
							return pDto;
						})
						.collect(Collectors.toList());
		return allProdDtos;*/
		return prodRepo.findByProdCategoryCategoryId(catgId)
						.stream()
						.map(ProductMapper::convertToDto)
						.collect(Collectors.toList());
	
	}

}
