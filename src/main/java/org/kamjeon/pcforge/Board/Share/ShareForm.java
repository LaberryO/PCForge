package org.kamjeon.pcforge.Board.Share;

import org.kamjeon.pcforge.Forge.Forge;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShareForm {

	@NotBlank
	@NotEmpty(message="제목은 필수 항목입니다.")
	@Size(max = 50)
	private String subject;
	
	@NotBlank
	@NotEmpty(message="내용은 필수 항목입니다.")
	private String content;
	
	private Forge forge;
}
