package in.ashokit.binding;

import lombok.Data;

@Data
public class UserRegistrationDTO {
	private Integer userId;
	private String userName;
	private String userEmail;
	private Long userPhNo;
	private String userPwd;
	private String pwdUpdated;

	private Integer countryId;
	private Integer stateId;//Bcoz database se id hi uth kar aati hai.As Foreign key value is a Digit in DB
	private Integer cityId;

}
