package in.ashokit.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer uId;
	
	@Column
	private String uName;
	
	@Column
	private Long phNo;
	
	@OneToMany(mappedBy="user",cascade=CascadeType.ALL)
	private List<Address> address;
	
	public User() {
		// TODO Auto-generated constructor stub
	}

}
