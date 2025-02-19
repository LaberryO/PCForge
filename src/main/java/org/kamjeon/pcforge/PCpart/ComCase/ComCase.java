package org.kamjeon.pcforge.PCpart.ComCase;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ComCase {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String formFactor;
	
	private String color;
	
	private String material;
	
	
}
