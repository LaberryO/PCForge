package org.kamjeon.pcforge.Board.Share;

import java.security.Principal;

import org.kamjeon.pcforge.Board.Comment.CommentForm;
import org.kamjeon.pcforge.Forge.Forge;
import org.kamjeon.pcforge.Forge.ForgeService;
import org.kamjeon.pcforge.User.SiteUser;
import org.kamjeon.pcforge.User.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/share")
@Controller
public class ShareController {
	private final UserService userService;
	private final ShareService shareService;
	private final ForgeService forgeService;
	
	// TODO 나중에 list 구현
	@GetMapping("/list")
	public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Share> paging = this.shareService.getList(page);
		model.addAttribute("paging", paging);
		
		return "share_board";
	}
	
	@GetMapping("/board/{id}")
	public String board(Model model, @PathVariable("id") int id, CommentForm comment) {
	    Share share = this.shareService.getShare(id);

	    // 조회수 증가 
	    int click = share.getClick();
	    share.setClick(click + 1);

	    model.addAttribute("share", share);
	    return "share_detail"; // 상세 페이지로 이동
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/create")
	public String create(Model model, @RequestParam("forgeId") Integer forgeId, ShareForm shareForm, Principal principal) {
		Forge forge = this.forgeService.getForge(forgeId).orElseThrow();
		shareForm.setForge(forge);
		model.addAttribute("forgeId", forgeId);
		return "share_form";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/create")
	public String create(@Valid ShareForm shareForm, BindingResult bindingResult, Principal principal) {
		if (bindingResult.hasErrors()) {
			return "share_form";
		}
		SiteUser siteUser = this.userService.getUser(principal.getName());
		Forge forgeTemp = shareForm.getForge();
		
		Share existingShare = this.shareService.getShareByForge(forgeTemp);
		if (existingShare != null) {
			forgeTemp = this.forgeService.copyForge(forgeTemp);
		}
		this.shareService.create(shareForm.getSubject(), shareForm.getContent(), siteUser, forgeTemp);
		return "redirect:/share/list";
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable("id") int id, ShareForm shareForm, Principal principal) {
		Share share = this.shareService.getShare(id);
		
		if(!share.getUser().getUserName().equals(principal.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없어야 이시키야");
		
		shareForm.setSubject(share.getSubject());
		shareForm.setContent(share.getContent());
		return ""; //수정 페이지로 이동 #kim
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String modify(@Valid ShareForm shareForm, BindingResult bind, @PathVariable("id") int id, Principal principal) {
		
		if(bind.hasErrors())
			return""; //여기는 고치는 페이지로 돌아가기 #kim
		Share share = this.shareService.getShare(id);
		
		if(!share.getUser().getUserName().equals(principal.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없어야 이시키야");
		
		this.shareService.modify(share, shareForm.getSubject(), shareForm.getContent());
		
		return "";//수정하고 나서 어딘가로 이동해야함  #kim
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, Principal principal) {
		Share share = this.shareService.getShare(id);
		
		if(!share.getUser().getUserName().equals(principal.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없어야 이시키야");
		
		this.shareService.delete(share);
		return ""; // 삭제하고나서 이동할 페이지   #Kim
	}
}
