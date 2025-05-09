package org.kamjeon.pcforge.Forge;

import java.util.List;

import org.kamjeon.pcforge.Board.Share.Share;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Forge {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	@ManyToOne
	private CPU cpu;

	@ManyToOne
	private RAM ram;

	@ManyToOne
	private GPU gpu;

	@ManyToOne
	private MBoard mboard;

	@ManyToOne
	private Disk disk;

	@ManyToOne
	private PSU psu;

	@ManyToOne
	private ComCase comCase;

	private Integer totalPrice;

	@PrePersist
	@PreUpdate
	public void setTotalPrice() {
		this.totalPrice = (cpu != null ? cpu.getPrice() : 0) + (ram != null ? ram.getPrice() : 0)
				+ (gpu != null ? gpu.getPrice() : 0) + (mboard != null ? mboard.getPrice() : 0)
				+ (disk != null ? disk.getPrice() : 0) + (psu != null ? psu.getPrice() : 0)
				+ (comCase != null ? comCase.getPrice() : 0);
	}

	// 세션 ID를 저장하기 위한 필드
	private String sessionId;
	
	@OneToOne
	private Share share;

	// 각 부품들의 사진들을 가져와서 저장 시키는 것
	private List<String> fileNames;
}
