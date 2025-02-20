package org.kamjeon.pcforge.Forge;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/forge")
public class ForgeController {
	private final ForgeService forgeService;

	
	//처음에 견적사이트 버튼 누르면
	@GetMapping("create")
	public String forge() {
		return "forge";
	}
}
