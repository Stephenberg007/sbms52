package in.ashokit.binding;

import lombok.Data;

@Data
public class ResetPasswordDTO {
private String email;
private String oldPassword;
private String newPassword;
private String confirmPassword;
}
