package org.kamjeon.pcforge.PCpart.GPU;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class GPU extends BaseProduct {
	
	
	
	private Float defaultSpeed;
	
	private Integer memorySize;
	
	private String type;
	
	private Integer powerConsumption;
	
	 
}
