package org.kamjeon.pcforge.PCpart;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class PCpart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long cpu_id;
	
	private Long ram_id;
	
	private Long gpu_id;
	
	private Long mboard_id;
	
	private Long disk_id;
	
	private Long case_id;
	
	private Long psu_id;
	
	private Long company_id;
	
}
