package in.ashokit.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private Integer productId;
	private String prodName;
	private String prodDesc;
	private String prodTitle;
	
	private String imageUrl;
	private Long unitPrice;
	private String status;
	private Integer unitsStock;
	
	private Integer categoryId;
}
