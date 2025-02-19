package org.kamjeon.pcforge.Board.Comment;

import java.time.LocalDateTime;
import java.util.Set;

import org.kamjeon.pcforge.Board.Share.Share;
import org.kamjeon.pcforge.User.SiteUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

// 예제에서 answer 랑 같은거
@Entity
@Getter
@Setter
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private LocalDateTime time;
	
	@ManyToOne
	private Share share;
	
	@ManyToOne
	private SiteUser user;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;
}
