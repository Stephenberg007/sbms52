package in.ashokit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
@Entity
@Setter
@Getter
public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer customerId;
	private String custName;
	
	
	@Column(unique=true,nullable=false)
	private String custEmail;
	
	private String pwd;
	private Long phNo;
	private String pwdUpdated;
	
//	@OneToMany(mappedBy="customer" , cascade=CascadeType.ALL)
//	private List<ShippingAddress> address;
//	
//	@OneToMany(mappedBy="customer" , cascade=CascadeType.ALL)
//	private List<Order> order;
//	
	
}
