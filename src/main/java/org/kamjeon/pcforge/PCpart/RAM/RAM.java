package org.kamjeon.pcforge.PCpart.RAM;

import org.kamjeon.pcforge.PCpart.Company.Company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RAM {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer capacity;
	
	private String type;
	
	private Integer speed;
	
	private Integer memoryChannel;
	
	@ManyToOne
	private Company makeCompany;
	
	private String fileName;
}
