package in.ashokit.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Contact {
	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	//private Integer id; for first two entries i used this
	@GeneratedValue(strategy=GenerationType.UUID)
	private String id;
	private String name;
	private String email;
	private long phoneno;
	
	
}
