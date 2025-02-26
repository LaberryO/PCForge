package org.kamjeon.pcforge.PCpart.Company;

import java.util.List;

import org.kamjeon.pcforge.PCpart.PCParts;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Disk.Disk;

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
	
	@OneToMany(mappedBy = "makeCompany")  // Disk 엔티티에서 makeCompany 필드와 매핑
	private List<ComCase> caess;
	
	@OneToMany(mappedBy = "makeCompany")  // Disk 엔티티에서 makeCompany 필드와 매핑
	private List<Disk> disks;
	
	  @ManyToOne
	  @JoinColumn(name = "pcparts_id", nullable = true) 
	  private PCParts pcPart;
	 
}
