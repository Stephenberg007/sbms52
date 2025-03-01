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

//!st thing done by us in project  i.e developing entities based on fields we want to SAVE in DB.+ Mapping
@Entity
@Setter
@Getter
@Table(name="counsellor_tbl")
public class Counsellor {
	
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer counsellorId;
private String name;
private String email;
private String password;
private Long phNo;

@OneToMany(mappedBy="counsellor", cascade=CascadeType.ALL)
private List<Enquiry> enquiries; 

}
