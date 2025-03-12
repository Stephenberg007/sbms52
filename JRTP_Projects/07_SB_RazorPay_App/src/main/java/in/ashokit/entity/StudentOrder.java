package in.ashokit.entity;

import java.math.BigInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class StudentOrder {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer orderId;
	
	private String name;
	private String email;
	private Long phNo;
	private String course;
	private Integer amount;
	
	private String orderStatus;
	
	private String razorpayOrderId;
	
}
