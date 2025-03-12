
package main.java.in.ashokit.entity;

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
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer addrId;
	private String houseNum;
	private String street;
	private String city;
	private String state;
	private String country;
	private Integer zipcode;
	
	@ManyToOne
	@JoinColumn(name="customer_id")
	private Customer customer;

}
