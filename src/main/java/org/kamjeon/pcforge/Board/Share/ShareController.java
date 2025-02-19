package org.kamjeon.pcforge.Board.Share;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/share")
@Controller
public class ShareController {
	private final ShareService shareService;
	
	// TODO 나중에 list 구현
	@GetMapping("/list")
	public String list() {
		
		return "Share_list";
	}
	
	
	@GetMapping("/write")
	public String writeIndex(ShareForm share) {
		
		return ""; //글 작성하는 곳으로 이동
	}
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/write/{id}")
	public String firstWrite() {
		return "";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/write")
	public String writeForge(@Valid ShareForm share, BindingResult bind) {
		
		return ""; //share리스트로 이동
	}
}
