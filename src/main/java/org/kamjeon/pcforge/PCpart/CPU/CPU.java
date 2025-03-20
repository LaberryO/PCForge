package org.kamjeon.pcforge.PCpart.CPU;

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

@Getter
@Setter
@Entity
public class CPU {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Float defaultSpeed;
	
	private Float maxSpeed;
	
	private String socket;
	
	private Integer coreCount;
	
	private Integer threadCount;
	
	private Integer ddr;
	
	private Integer ddrSpeed;
	
	private Integer memoryChannel;
	
	private Boolean innerGPU;
	
	private String fileName;
	
	private Integer price;
	

	private Integer makeCompany;
	
	@JsonIgnore
   	@ManyToOne
	@JoinColumn(name = "pcparts_id", nullable = true)
	private PCParts pcPart;
}
