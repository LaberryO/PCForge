package org.kamjeon.pcforge.Product;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@Controller
@RequiredArgsConstructor
public class ProductController {
	
	@GetMapping("/create")
	public String create(Model model) {
		
		model.addAttribute("questionForm", new ProductForm());
		return "productSelect";
	}
	
}
