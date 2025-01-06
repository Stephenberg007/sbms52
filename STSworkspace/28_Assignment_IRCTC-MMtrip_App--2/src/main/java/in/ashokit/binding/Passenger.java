package in.ashokit.binding;

import lombok.Data;

@Data
public class Passenger {
	private Integer ticketId;
	private String ticketStatus;
	private String trainNum;
	private String name;
	private String email;
	private String dob;
	private String gender;
	private String doj;
	private String source;
	private String destination;
}
