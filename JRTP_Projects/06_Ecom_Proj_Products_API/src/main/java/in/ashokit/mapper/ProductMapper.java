package in.ashokit.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ashokit.dto.ProductDTO;
import in.ashokit.entity.Product;

@Component
public class ProductMapper {
	
	private static final  ModelMapper mapper= new ModelMapper();
	
	
	
	public static Product convertToEntity(ProductDTO productDto) {
		return mapper.map(productDto, Product.class);
	}
	
	public static ProductDTO convertToDto(Product product) {
		return mapper.map(product, ProductDTO.class);
	}

}
