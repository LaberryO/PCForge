package org.kamjeon.pcforge.Boards;


import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("starter")
@RequiredArgsConstructor
@Controller
public class StarterController {
	private final StarterService starterService;
	

	@GetMapping("/create")
	public String create() {
		return "starter";
	}
	
	@PostMapping("/create")
	public String create(@Valid StarterForm starter,  BindingResult bindingResult) {
		
		if(bindingResult.hasErrors()) {
			return "starter";
		}
	
		this.starterService.create(starter.getTitle(), starter.getImage(), starter.getContent());
		return "redirect:/";
	}
	
	
}
