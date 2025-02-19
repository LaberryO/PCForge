package org.kamjeon.pcforge.PCpart.Disk;

import org.kamjeon.pcforge.PCpart.Company.Company;

public class Disk {
	
	private Integer id;
	
	private String name;
	
	private Integer capacity;
	
	private String type;
	
	private Integer speed;
	
	// ManyToOne
	private Company makeCompany;
}
