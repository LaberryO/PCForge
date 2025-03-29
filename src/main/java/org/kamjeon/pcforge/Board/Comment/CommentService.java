package org.kamjeon.pcforge.Board.Comment;

import java.time.LocalDateTime;
import java.util.Optional;

import org.kamjeon.pcforge.Board.Share.Share;
import org.kamjeon.pcforge.User.SiteUser;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {
	private final CommentRepository commentRepository;
	
	public Comment create(SiteUser user, Share share, String content) {
		Comment com = new Comment();
		com.setContent(content);
		com.setUser(user);
		com.setShare(share);
		com.setTime(LocalDateTime.now());
		
		this.commentRepository.save(com);

		return com;
	}
	
	public Comment getComment(int id) {
		Optional<Comment> com = this.commentRepository.findById(id);
		if(com.isEmpty())
			return null;
		Comment comment = com.get();
		return comment;
	}
	
	public void modify(Comment com, String content) {
		com.setContent(content);
		com.setModifyDate(LocalDateTime.now());
		this.commentRepository.save(com);
	}
	
	public void delete(Comment com) {
		this.commentRepository.delete(com);
	}
}
