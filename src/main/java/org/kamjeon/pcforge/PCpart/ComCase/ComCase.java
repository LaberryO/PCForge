package org.kamjeon.pcforge.PCpart.ComCase;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ComCase extends BaseProduct {

	
	private String formFactor;

	private String color;

	private String material;

	private Integer fanSupport;



	 

}
