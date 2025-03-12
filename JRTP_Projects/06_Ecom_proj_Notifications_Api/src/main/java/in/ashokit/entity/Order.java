package in.ashokit.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
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
@Table(name="order_table")
@Setter
@Getter
public class Order {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	private String orderTrackingNum;
	private Integer totalQuantity;
	private Double totalPrice;
	private String orderStatus;
	private String email;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDateTime dateCreated;
	@UpdateTimestamp
	private LocalDate lastUpdated;
	private LocalDate deliveryDate;
	
	private String paymentStatus;
	private String razorpayOrderId;// generated when payment is initiated
	private String razorpayPaymentId;// generated when payment is completed
	private String invoiceUrl;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="addr_id")
	private ShippingAddress address;

}
