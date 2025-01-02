package in.ashokit.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Product {
		@Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private Integer pid;
		private String pname;
		private String pcode;
		@Lob
		private Byte[] image;
		
}
