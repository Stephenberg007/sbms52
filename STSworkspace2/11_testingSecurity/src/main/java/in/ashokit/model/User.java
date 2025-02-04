package in.ashokit.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class User {
	
	private String name;
	private String city;
	private Long salary;
}