package in.ashokit.dto;

import java.util.Date;

import in.ashokit.entity.Customer;
import lombok.Data;

@Data
public class CustomerDto {
	private Integer customerId;
	private String custName;
	
	private String custEmail;
	
	private String pwd;
	private Long phNo;
	
	
	private Date dateCreated;
	private Date lastUpdated;
	

	
}
