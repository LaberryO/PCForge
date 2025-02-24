package org.kamjeon.pcforge.Board.Comment;

import java.security.Principal;

import org.kamjeon.pcforge.Board.Share.Share;
import org.kamjeon.pcforge.Board.Share.ShareService;
import org.kamjeon.pcforge.User.SiteUser;
import org.kamjeon.pcforge.User.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

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
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/modify/{id}")
	public String modify(@PathVariable("id") int id, CommentForm comment, Principal principal) {
		Comment com = this.commentService.getComment(id);
		
		if(!com.getUser().getUserName().equals(principal.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정 권한이 없습니다");
		
		comment.setContent(com.getContent());
		return ""; //수정할 페이지로 이동  #Kim
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/modify/{id}")
	public String modify(@PathVariable("id") int id, @Valid CommentForm commet, BindingResult bind, Principal principal ) {
		if(bind.hasErrors())
			return ""; //오류시 되돌아갈 페이지로 이동 #Kim
		
		Comment com = this.commentService.getComment(id);
		if(com.getUser().getUserName().equals(principal.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "수정할 수 없단다 간난뱅이 시키야");
		
		this.commentService.modify(com, commet.getContent());
		return String.format(""); // 답변 수정하고 이동할 페이지 #Kim 
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") int id, Principal principal) {
		Comment com = this.commentService.getComment(id);
		
		if(com.getUser().getUserName().equals(principal.getName()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제 권한이 없다 시발놈아");
		
		this.commentService.delete(com);
		return ""; //삭제하고나서 되돌아갈 페이지 입력 #Kim
	}
}
