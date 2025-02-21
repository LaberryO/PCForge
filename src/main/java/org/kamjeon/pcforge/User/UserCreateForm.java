package org.kamjeon.pcforge.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {
	
	@Size(min=3, max=25, message = "ID는 3자 이상 25자 이하로 작성해야합니다.")
	@NotBlank(message="사용자 ID는 필수 사항입니다.")
	private String username;
	
	@NotBlank(message="비밀번호는 필수 사항입니다.")
	private String password;
	
	@NotBlank(message="비밀번호 확인은 필수 사항입니다.")
	private String checkPassword;
	
	@NotBlank(message="이메일은 필수 사항입니다.")
	@Email
	private String email;
}
