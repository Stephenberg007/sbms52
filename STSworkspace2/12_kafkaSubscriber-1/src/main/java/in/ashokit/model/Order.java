package in.ashokit.model;

import lombok.Data;

@Data
public class Order {
	private String id;
	private Double price;
	private String email;
	// We want to store Order Data as Kafka Topic
}
