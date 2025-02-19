package org.kamjeon.pcforge.PCpart.Disk;

import org.kamjeon.pcforge.PCpart.Company.Company;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Disk {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private Integer capacity;
	
	private String type;
	
	private Integer speed;
	
	@ManyToOne
	private Company makeCompany;
}
