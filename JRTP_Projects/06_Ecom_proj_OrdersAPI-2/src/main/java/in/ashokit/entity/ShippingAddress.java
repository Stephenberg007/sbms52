
package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity

@Setter
@Getter
public class ShippingAddress {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String addrId;
	private String houseNum;
	private String street;
	private String city;
	private String state;
	private String country;
	private Integer zipcode;

}
