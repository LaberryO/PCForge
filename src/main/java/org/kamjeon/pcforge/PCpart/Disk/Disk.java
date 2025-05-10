package org.kamjeon.pcforge.PCpart.Disk;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Disk extends BaseProduct{
	
	
	private Integer capacity;
	
	private String type;
	
	private Integer speed;
	
	 
}
