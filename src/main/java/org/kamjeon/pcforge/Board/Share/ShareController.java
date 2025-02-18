package org.kamjeon.pcforge.Board.Share;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/share")
@Controller
public class ShareController {
	private final ShareService shareService;
	
	//DOTO 나중에 list 구현
	@GetMapping("/list")
	public String list() {
		
		return "Share_list";
	}
	
	@GetMapping("/write")
	public String writeIndex() {
		
		return "";
	}
	
	@PostMapping("/write")
	public String writeForge() {
		
		return "";
	}
}
