package org.kamjeon.pcforge.Boards;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StarterForm {
	
	@NotBlank
	@NotEmpty(message="제목은 필수 항목입니다.")
	@Size(max = 25)
	private String title;

	@NotNull
	private MultipartFile  image;
	
	@NotBlank
	@NotEmpty(message="내용은 필수 항목입니다.")
	@Size(max = 10000, message="내용이 너무 깁니다.")
	private String content;
	
	  @NotNull(message = "타입을 선택해야 합니다.")
	  private StarterType type;  
}
