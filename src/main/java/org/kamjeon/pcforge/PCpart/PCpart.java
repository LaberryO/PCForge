package org.kamjeon.pcforge.PCpart;

import java.util.List;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Entity
public class PCpart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private List<CPU> cpuList;
	
	private List<RAM> ramList;
	
	private List<GPU> gpuList;
	
	private List<MBoard> mBoardList;
	
	private List<Disk> diskList;
	
	private List<PSU> psuList;
	
	private List<ComCase> comCase;
}
