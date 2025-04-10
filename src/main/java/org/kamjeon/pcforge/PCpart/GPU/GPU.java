package org.kamjeon.pcforge.PCpart.GPU;

import org.kamjeon.pcforge.PCpart.BaseProduct;
import org.kamjeon.pcforge.PCpart.PCParts;
import org.kamjeon.pcforge.PCpart.Company.Company;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
