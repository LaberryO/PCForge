package org.kamjeon.pcforge.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
	
	private final UserService userService;
	
	@GetMapping("/sign-up")
	public String signUp(UserCreateForm userCreateForm, Model model) {
		model.addAttribute("namePage", "sign-up");
		model.addAttribute("ifRegister", true);
		model.addAttribute("hideNavbar", true);
		model.addAttribute("titlePage", "회원가입");
		model.addAttribute("btnPage", "등록");
		return "user_form";
	}
	
	@GetMapping("/sign-in")
	public String signIn(Model model) {
		String str = "로그인";
		model.addAttribute("namePage", "sign-in");
		model.addAttribute("hideNavbar", true);
		model.addAttribute("titlePage", str);
		model.addAttribute("btnPage", str);
		return "user_form";
	}
}
