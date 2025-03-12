package in.ashokit.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import in.ashokit.dto.ProductCategoryDTO;
import in.ashokit.dto.ProductDTO;
import in.ashokit.entity.ProductCategory;

@Component
public class ProductCategoryMapper {
	private static final ModelMapper mapper= new ModelMapper();
	
	 
	
	
	public static ProductCategory convertToEntity(ProductCategoryDTO prodCatgDto) {
		return mapper.map(prodCatgDto, ProductCategory.class);
	}
	
	public static ProductCategoryDTO convertToDto(ProductCategory prodCatg) {
		return mapper.map(prodCatg, ProductCategoryDTO.class);
	}
	
}
