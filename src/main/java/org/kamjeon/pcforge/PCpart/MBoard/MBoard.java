package org.kamjeon.pcforge.PCpart.MBoard;

import org.kamjeon.pcforge.PCpart.PCParts;
import org.kamjeon.pcforge.PCpart.Company.Company;

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
public class MBoard {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String socket;
	
	private String formFactor;
	
	private Integer maxMemory;
	
	private String ddrSupport;
	
	private Integer PCISlots;
	
	@ManyToOne
	private Company makeCompany;
	
	private String fileName;
	
	private int price;
	
	
	  @ManyToOne
	  @JoinColumn(name = "pcparts_id", nullable = true) 
	  private PCParts pcPart;
	 
}
