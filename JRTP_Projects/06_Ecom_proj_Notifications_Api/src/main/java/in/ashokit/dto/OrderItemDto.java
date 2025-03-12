package in.ashokit.dto;

import lombok.Data;

@Data
public class OrderItemDto {
	
	private Integer orderItemId;
	private String imageUrl;
	private String productname;
	private Integer quantity;
	private Long unitPrice;
	
//	private Integer productId;
//	private Integer orderId;
}
