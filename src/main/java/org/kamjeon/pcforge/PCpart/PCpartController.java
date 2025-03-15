package org.kamjeon.pcforge.PCpart;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class PCpartController {
	private final PCpartService pcPartService;
	
	@GetMapping("/list")
    public String list(Model model, 
            @RequestParam(value = "type", defaultValue = "All") String type, 
            @RequestParam(value = "page", defaultValue = "0") int page, 
            @RequestParam(value = "kw", defaultValue = "") String kw) {

		 Page<PCParts> paging = this.pcPartService.getList(page, kw, type); 
	        model.addAttribute("paging", paging);
	        model.addAttribute("kw", kw);
	        model.addAttribute("type", type);
	        
		return "shop"; // 이동 
	}
	
}
