package in.ashokit.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Passport {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer passportId;
	private String passportNumber;
	private LocalDate issuedAt;
	private LocalDate expiringOn;
	
	@OneToOne
	@JoinColumn(name="person_id")
	private Person person;

	@Override
	public String toString() {
		return "Passport [expiringOn=" + expiringOn + ", issuedAt=" + issuedAt + ", passportId=" + passportId
				+ ", passportNumber=" + passportNumber + ", person=" + person + "]";
	}
	
	
	
}