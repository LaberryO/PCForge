package org.kamjeon.pcforge.Board.Comment;

import java.security.Principal;

import org.kamjeon.pcforge.Board.Share.Share;
import org.kamjeon.pcforge.Board.Share.ShareService;
import org.kamjeon.pcforge.User.SiteUser;
import org.kamjeon.pcforge.User.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/comment")
@Controller
public class CommentController {
	private final CommentService commentService;
	private final ShareService shareService;
	private final UserService userService;
	
	/*
	 * @PreAuthorize("isAuthenticated()")
	 * 
	 * @GetMapping("/write") public String write(CommentForm comment) { return "";
	 * //답변 등록하러가는 페이지 #Kim }
	 */
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/write{id}")
	public String write(Model model, @PathVariable("id") int id, @Valid CommentForm comment, BindingResult bind, Principal principal) {
		Share share = this.shareService.getShare(id);
		if(bind.hasErrors()) {
			model.addAttribute("share", share);
			return ""; //다시 작성하는 페이지로 돌아가기 #Kim
		}
	
		SiteUser user =  this.userService.getUser(principal.getName());
		Comment com = this.commentService.create(user, share, comment.getContent());
		
		return ""; //전부 작성하고 이동할 페이지 #Kim
	}
}
