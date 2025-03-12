package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity

@Getter
@Setter
public class OrderItem {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderItemId;
	
	private String prodName;
	private String imageUrl;
	private Integer quantity;
	private Long unitPrice;
	
//	@ManyToOne
//	@JoinColumn(name="product_id")
//	private Product product;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Order order;
	

}
