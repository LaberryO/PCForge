package org.kamjeon.pcforge.PCpart.MBoard;

import org.kamjeon.pcforge.PCpart.Company.Company;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	
	private String ddrSuppory;
	
	private Integer PCISlots;
	
	@ManyToOne
	private Company makeCompany;
	
	private String fileName;
}
