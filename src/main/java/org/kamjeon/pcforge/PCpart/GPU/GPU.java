package org.kamjeon.pcforge.PCpart.GPU;

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
public class GPU {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Float defaultSpeed;
	
	private Integer memorySize;
	
	private String type;
	
	@ManyToOne
	private Company makeCompany;
	
	private Integer powerConsumption;
	
	private String fileName;
	
	private int price;
	
	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "pcparts_id") private PCParts pcPart;
	 */
}
