package org.kamjeon.pcforge.User;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	private void fSignUp(Model model) {
		model.addAttribute("namePage", "sign-up");
		model.addAttribute("ifRegister", true);
		model.addAttribute("hideNavbar", true);
		model.addAttribute("titlePage", "회원가입");
		model.addAttribute("btnPage", "등록");
	}
	
	@GetMapping("/sign-up")
	public String signUp(UserCreateForm userCreateForm, Model model) {
		fSignUp(model);
		return "user_form";
	}
	
	@GetMapping("/sign-in")
	public String signIn(UserCreateForm userCreateForm, Model model) {
		String str = "로그인";
		model.addAttribute("namePage", "sign-in");
		model.addAttribute("hideNavbar", true);
		model.addAttribute("titlePage", str);
		model.addAttribute("btnPage", str);
		return "user_form";
	}
	
	@PostMapping("/sign-up")
	public String signUp(@Valid UserCreateForm userCreateForm, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			fSignUp(model);
			return "user_form";
		}
		if(!userCreateForm.getPassword().equals(userCreateForm.getCheckPassword())) {
			bindingResult.rejectValue("password", "pwErorr", "두 비밀번호가 맞지 않습니다" );
			fSignUp(model);
			return "user_form";
		}
	
		try {
			userService.createUser(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword());
		}catch(DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			fSignUp(model);
			return "user_form";
		}catch(Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			fSignUp(model);
			return "user_form";
		}
		
		return "redirect:/";
	}
	
}
