package in.ashokit.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {
	private String custEmail;
	private String oldPassword;
	private String newPassword;
	private String cnfmPassword;

}
