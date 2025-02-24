package org.kamjeon.pcforge.PCpart.Company;

import org.kamjeon.pcforge.PCpart.PCParts;

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
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private String country;
	
	@ManyToOne
	@JoinColumn(name = "pcparts_id")
	private PCParts pcPart;
}
