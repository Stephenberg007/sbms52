package main.java.in.ashokit.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

import main.java.in.ashokit.dto.ProductDTO;
import main.java.in.ashokit.entity.Product;

@Configuration
public class ProductMapper {
	
	
	private static final ModelMapper mapper= new ModelMapper();
	
	public static Product convertToEntity(ProductDTO productDTO) {
		return mapper.map(productDTO, Product.class);
	}
	
	public static ProductDTO convertToDto(Product product) {
		return mapper.map(product, ProductDTO.class);
	}
	
}
