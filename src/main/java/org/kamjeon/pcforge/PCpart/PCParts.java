package org.kamjeon.pcforge.PCpart;

import java.util.List;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Company.Company;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PCParts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String test;

	/*
	 * @OneToMany(mappedBy = "pcPart") private List<ComCase> comCase;
	 * 
	 * @OneToMany(mappedBy = "pcPart") private List<CPU> cpu;
	 * 
	 * @OneToMany(mappedBy = "pcPart") private List<Disk> disk;
	 * 
	 * @OneToMany(mappedBy = "pcPart") private List<GPU> gpu;
	 * 
	 * @OneToMany(mappedBy = "pcPart") private List<MBoard> mBoard;
	 * 
	 * @OneToMany(mappedBy = "pcPart") private List<PSU> psu;
	 * 
	 * @OneToMany(mappedBy = "pcPart") private List<RAM> ram;
	 * 
	 * @OneToMany(mappedBy = "pcPart") private List<Company> company;
	 */
}
