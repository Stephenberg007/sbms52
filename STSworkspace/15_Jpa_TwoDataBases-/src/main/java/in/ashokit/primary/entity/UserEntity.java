package in.ashokit.primary.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="USERENTITY")
@Data
@NoArgsConstructor
public class UserEntity {
@Id
private Integer uid;
private String uname;
}
