package in.ashokit.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity

@Setter
@Getter
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer productId;
	private String prodName;
	private String prodDesc;
	private String prodTitle;
	
	private String imageUrl;
	private BigDecimal unitPrice;
	private String status;
	private Integer unitsStock;
	
	@CreationTimestamp
	@Column(name="creation_date",updatable=false)
	private LocalDateTime dateCreated;
	
	@UpdateTimestamp
	private LocalDateTime lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private ProductCategory prodCategory;
	
	

}
