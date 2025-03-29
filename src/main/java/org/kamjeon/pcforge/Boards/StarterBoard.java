package org.kamjeon.pcforge.Boards;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class StarterBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Integer id;
	
	//승재가 forge 연동을 하고 싶어한다
	private String image;
	private String title;
	
    @Lob
	@Column(columnDefinition = "TEXT")
	private String content;
    
	
	private LocalDateTime createDate;
	private StarterType type;
}
