package org.kamjeon.pcforge.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
	
	@GetMapping("/sign-up")
	public String signUp(UserCreateForm userCreateForm) {
		return "signup_form";
	}
	
	@GetMapping("/sign-in")
	public String signIn() {
		return "signin_form";
	}
	
	@PostMapping("/sign-up")
	public String signUp(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "signup_form";
		}
		if(!userCreateForm.getPassword().equals(userCreateForm.getCheckPassword())) {
			bindingResult.rejectValue("password", "pwErorr", "비밀번호가 일치하지 않습니다" );
			return "signup_form";
		}
	
		try {
			userService.createUser(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}
		
		return "redirect:/";
	}
	
	@PostMapping("/api/get-email")
	@PreAuthorize("isAuthenticated()") // 인증된 사용자만 접근 가능
	public ResponseEntity<String> getEmailById() {
		// 현재 로그인된 사용자 정보 가져오기
	    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	    if (principal instanceof UserDetails) {
	        String username = ((UserDetails) principal).getUsername(); // username 가져오기
	        SiteUser user = userService.getUser(username);
	        String email = user.getEmail();
	        return ResponseEntity.ok(email);
	    } else {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인되지 않은 사용자입니다.");
	    }
	}
}
