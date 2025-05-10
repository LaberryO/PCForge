package org.kamjeon.pcforge.PCpart.RAM;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RAM extends BaseProduct{

	

	private Integer capacity;

	private String type;

	private Integer speed;

	private Integer memoryChannel;


}
