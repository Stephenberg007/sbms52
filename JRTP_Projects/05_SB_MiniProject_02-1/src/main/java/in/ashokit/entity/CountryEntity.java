package in.ashokit.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="country_master")
@Setter
@Getter
public class CountryEntity {
	@Id
	private Integer countryId;
	private String countryName;
	
//	@OneToMany(mappedBy="country_id",cascade=CascadeType.ALL)
//	private List<StateEntity> state;  Double side association Mapping is Not needed bcoz as per nneded output We need to go only in 
									// forward Direction.
	
}
