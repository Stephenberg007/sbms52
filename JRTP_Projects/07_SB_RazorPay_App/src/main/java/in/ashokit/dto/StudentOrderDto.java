package in.ashokit.dto;

import java.math.BigInteger;

import lombok.Data;


@Data
public class StudentOrderDto {
	
	private Integer orderId;
	
	private String name;
	private String email;
	private Long phNo;
	private String course;
	private Integer amount;
	private String orderStatus;
	
	private String razorpayOrderId;
}
