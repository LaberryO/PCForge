package org.kamjeon.pcforge.Forge;

import org.kamjeon.pcforge.PCpart.PCpartUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/forge")
public class ForgeController {
	private final ForgeService forgeService;

	//처음에 견적사이트 버튼 누르면
	@GetMapping("create")
	public String forge(Forge forge) {
		return "forge";
	}

	@GetMapping("create/{status}")
	public String gotoStatus(@PathVariable("status") String status, Model model, Forge forge) {
	    try {
	    	if (!"final".equals(status)) {
	    		PCpartUtils.checkPCPart(status.toLowerCase());
	    	}
	    	model.addAttribute("status", status);
	    } catch (IllegalArgumentException e) {
	    	System.out.println("에러 발생: " + e.getMessage());
	    }
	    return "forge";
	}

}
