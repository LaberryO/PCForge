package org.kamjeon.pcforge.Board.Share;



import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.kamjeon.pcforge.Board.Comment.Comment;
import org.kamjeon.pcforge.Forge.Forge;
import org.kamjeon.pcforge.User.SiteUser;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

// 예제에서 question 이랑 같은거
@Entity
@Getter
@Setter
public class Share {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	private List<String> file;
	
	private LocalDateTime time;
	
	@OneToMany(mappedBy = "share", cascade = CascadeType.REMOVE)
	private List<Comment> commentList;
	
	@ManyToOne
	private SiteUser user;
	
	private LocalDateTime modifyDate;
	
	@ManyToMany
	Set<SiteUser> voter;

	@OneToOne
	private Forge forge;
	
	private Integer click; //조회수
	
}
