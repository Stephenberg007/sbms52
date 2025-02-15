package in.ashokit.entity;

import java.time.LocalDateTime;

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

@Setter
@Getter
public class Order {//storing details of Combined Order
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	private String OrderTrackingNum;
	private Integer totalQuantity;
	private Long totalPrice;
	private String orderStatus;
	
	private LocalDateTime dateCreated;
	private LocalDateTime lastUpdated;
	private LocalDateTime deliveryDate;
	
	private String paymentStatus;
	private String razorPayOrderId;
	private String razorPayPaymentId;
	private String invoiceUrl;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne
	@JoinColumn(name="addr_id")
	private ShippingAddress address;

}
