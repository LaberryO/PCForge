package org.kamjeon.pcforge.Board.Share;

import org.kamjeon.pcforge.User.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/share")
@Controller
public class ShareController {
	private final UserService userService;
	private final ShareService shareService;
	
	// TODO 나중에 list 구현
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Share> paging = this.shareService.getList(page);
		model.addAttribute("paging", paging);
		
		return "Share_list";
	}
}
