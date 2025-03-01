package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="enquiry_tbl")
public class Enquiry {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer enquiry_id;
private String stuName;
private Long stuPhNo;
private String classMode;
private String course;
private String enqStatus;

@ManyToOne
@JoinColumn(name="Counsellor_id")
private Counsellor counsellor;

	
}
