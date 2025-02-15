package in.ashokit.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
	private Long unitPrice;
	private Integer unitsStock;
	 @Enumerated(EnumType.STRING)  // Store as String in DB
	    private ProductStatus status;
	
	

		@CreationTimestamp
		@Column(name="creation_date",updatable=false)
		private LocalDateTime dateCreated;
		
		@UpdateTimestamp
		private LocalDateTime lastUpdated;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private ProductCategory prodCategory;
	
}

 enum ProductStatus {
    ACTIVE,
    INACTIVE
}

