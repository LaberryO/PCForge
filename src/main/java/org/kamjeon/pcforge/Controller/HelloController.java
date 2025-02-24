package org.kamjeon.pcforge.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	@GetMapping("/sub")
	public String testSub() {
		return "main";
	}
	
	@GetMapping("/sub/chart")
	public String testSub2() {
		return "charts";
	}
}
