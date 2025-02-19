package org.kamjeon.pcforge.PCpart.CPU;

import org.kamjeon.pcforge.PCpart.Company.Company;

public class CPU {

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
	
	private Integer balance;
	
	// ManyToOne
	private Company makeCompany;
}
