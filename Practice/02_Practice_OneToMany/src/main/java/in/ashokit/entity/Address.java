package in.ashokit.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Address {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer addId;

@Column
private String city;

@Column
private String state;

@ManyToOne
@JoinColumn(name="user_id")
private User user;
	
}
