package org.kamjeon.pcforge.PCpart.Company;

import java.util.List;

import org.kamjeon.pcforge.PCpart.PCParts;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer company_id;
	 
	private String name;
	
	private String country;
	
	 @OneToMany(mappedBy = "myCom", cascade = CascadeType.ALL)
	 private List<CPU> cpus;
	 
	 @OneToMany(mappedBy = "myCom", cascade = CascadeType.ALL)
	 private List<ComCase> comcases;
	 
	 @OneToMany(mappedBy = "myCom", cascade = CascadeType.ALL)
	 private List<Disk> disks;
	 
	 @OneToMany(mappedBy = "myCom", cascade = CascadeType.ALL)
	 private List<GPU> gpus;
	 
	 @OneToMany(mappedBy = "myCom", cascade = CascadeType.ALL)
	 private List<MBoard> mBoards;
	 
	 @OneToMany(mappedBy = "myCom", cascade = CascadeType.ALL)
	 private List<PSU> psus;
	 
	 @OneToMany(mappedBy = "myCom", cascade = CascadeType.ALL)
	 private List<RAM> rams;
	 

	 
	 
	  @ManyToOne
	  @JoinColumn(name = "pcparts_id", nullable = true) 
	  private PCParts pcPart;
	 
}
