package org.kamjeon.pcforge.Boards;


import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("starter")
@RequiredArgsConstructor
@Controller
public class StarterController {
	private final StarterService starterService;
	

	@GetMapping("/create")
	public String create(StarterForm starter, Model model) {
		model.addAttribute("types", StarterType.values());
		return "starter_form";
	}
	
	@PostMapping("/create")
	public String create(@Valid StarterForm starter,  BindingResult bindingResult, Model model) {
        MultipartFile image = starter.getImage();
        
        if (image == null || image.isEmpty()) {
            bindingResult.rejectValue("image", "error.image", "이미지를 업로드해야 합니다.");
        }
		if(bindingResult.hasErrors()) {
			 model.addAttribute("types", StarterType.values());
			return "starter_form";
		}
		
		String imageName = null;
        try {
            imageName = starterService.saveIamge(image); 
        } catch (IOException e) {
        	bindingResult.rejectValue("image", "error.image", "이미지 저장 실패");
            return "starter_form";
        }  
	
		this.starterService.create(starter.getTitle(), imageName, starter.getContent(), starter.getType());
		return "redirect:/";
	}
	
	
}
