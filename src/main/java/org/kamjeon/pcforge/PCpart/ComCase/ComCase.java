package org.kamjeon.pcforge.PCpart.ComCase;

import org.kamjeon.pcforge.PCpart.BaseProduct;
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
public class ComCase extends BaseProduct {

	
	private String formFactor;

	private String color;

	private String material;

	private Integer fanSupport;



	 

}
