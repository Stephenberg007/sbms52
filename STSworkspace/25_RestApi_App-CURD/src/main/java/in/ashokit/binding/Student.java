package in.ashokit.binding;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Studento")
public class Student {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer sid;
	
private String name;
private String course;
}
