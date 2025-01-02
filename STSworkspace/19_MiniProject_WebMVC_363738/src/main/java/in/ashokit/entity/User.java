package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Data
@Table(name="user_tbl")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer uid;
	
	@NotEmpty(message="Name is mandatory")
	String name;
	
	@NotNull(message="pls Enter Number")
	Long phone;
	
	@NotEmpty(message="Email is mandatory")
	@Email(message="Enter valid email")
	String email;
	
	@CreationTimestamp
	@Column(updatable=false)
	private LocalDate createdAt;
	@UpdateTimestamp
	@Column(insertable=false)
	private LocalDate lastupdatedAt;

}
