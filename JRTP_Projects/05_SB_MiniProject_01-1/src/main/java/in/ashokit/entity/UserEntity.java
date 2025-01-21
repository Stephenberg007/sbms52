package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="user_master")
public class UserEntity {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer userId;
private String userName;
private String userEmail;
private Long userPhNo;
private String userPwd;
private String pwdUpdated;


@ManyToOne
@JoinColumn(name="country_id")
private CountryEntity country;// In DB it will be saved as a digit i.e id from country_master table.

@ManyToOne
@JoinColumn(name="state_id")
private StateEntity state;// same here

@ManyToOne()
@JoinColumn(name="city_id")
private CityEntity city;

	
}
