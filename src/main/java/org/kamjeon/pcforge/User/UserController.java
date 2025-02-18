package org.kamjeon.pcforge.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/signUp")
	public String signUp(UserCreateForm userCreateForm) {
		
		return "signUp";
	}
	
	@PostMapping("/signUp")
	public String signUp(@Valid UserCreateForm userCreateForm, BindingResult bindingResult ) {
		if(bindingResult.hasErrors()) {
			return "signUp";
		}
		if(!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password1", "pwErorr", "두 비밀번호가 맞지 않습니다" );
			return "signUp";
		}
	
		try {
			userService.CreateUser(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signUp";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signUp";
		}
		
		return "redirect:/";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
	
}
