package org.kamjeon.pcforge.PCpart.ComCase;

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

@Getter
@Setter
@Entity
public class ComCase {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;

	private String formFactor;

	private String color;

	private String material;

	private Integer fanSupport;


	private String fileName;

	private int price;

	private Integer makeCompany;

	
	  @ManyToOne
	  @JoinColumn(name = "pcparts_id")
	  private PCParts pcPart;
	 

}
