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
	
	private int capacity;
	
	private String type;
	
	private int speed;
	
	private int memoryChannel;
	
	//다대일
	private Company makeCompany;
	
	private String fileName;
}
