package in.ashokit.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
	
	@Email
	@NotBlank(message="Email is Needed")
	@Column(unique=true,nullable=false)
	private String custEmail;
	
	private String pwd;
	private Long phNo;
	private String pwdUpdated;
	
	@OneToMany(mappedBy="customer" , cascade=CascadeType.ALL)
	private List<ShippingAddress> address;
	
	
}
