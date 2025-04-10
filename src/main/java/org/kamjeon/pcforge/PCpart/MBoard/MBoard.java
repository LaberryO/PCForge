package org.kamjeon.pcforge.PCpart.MBoard;

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
public class MBoard extends BaseProduct{
	
	
	
	private String socket;
	
	private String formFactor;
	
	private Integer maxMemory;
	
	private String ddrSupport;
	
	private Integer PCISlots;
	
	 
}
