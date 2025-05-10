package org.kamjeon.pcforge.PCpart.MBoard;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class MBoard extends BaseProduct{
	
	
	
	private String socket;
	
	private String formFactor;
	
	private Integer maxMemory;
	
	private String ddrSupport;
	
	private Integer PCISlots;
	
	 
}
