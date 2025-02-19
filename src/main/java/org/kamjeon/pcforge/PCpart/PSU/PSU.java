package org.kamjeon.pcforge.PCpart.PSU;

import org.kamjeon.pcforge.PCpart.Company.Company;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;

// 파워 서플라이
@Entity
@Getter
@Setter
public class PSU {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	
	private int wattage;
	
	private String efficency;
	
	private String formFactor;
	
	//다대일
	private Company makeCompany;
	
	private String fileName;
}
