package in.ashokit.dto;

import lombok.Data;

@Data
public class ShippingAddressDto {
	private Integer addrId;
	private String houseNum;
	private String street;
	private String city;
	private String state;
	private String country;
	private Integer zipcode;
	private Integer customerId;
}
