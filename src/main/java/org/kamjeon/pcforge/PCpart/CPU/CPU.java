package org.kamjeon.pcforge.PCpart.CPU;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CPU extends BaseProduct {
	
	
	private Float defaultSpeed;
	
	private Float maxSpeed;
	
	private String socket;
	
	private Integer coreCount;
	
	private Integer threadCount;
	
	private Integer ddr;
	
	private Integer ddrSpeed;
	
	private Integer memoryChannel;
	
	private Boolean innerGPU;
	
}
