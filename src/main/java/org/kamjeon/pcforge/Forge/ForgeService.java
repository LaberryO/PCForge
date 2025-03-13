package org.kamjeon.pcforge.Forge;

import java.util.Optional;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.CPU.CPURepository;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.ComCase.ComCaseRepository;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.Disk.DiskRepository;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.GPU.GPURepository;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.MBoard.MBoardRepository;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.PSU.PSURepository;
import org.kamjeon.pcforge.PCpart.RAM.RAM;
import org.kamjeon.pcforge.PCpart.RAM.RAMRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ForgeService {
	private final ForgeRepository forgeRepository;
	private final ComCaseRepository comCaseRepository;
	private final CPURepository cpuRepository;
	private final DiskRepository diskRepository;
	private final GPURepository gpuRepository;
	private final MBoardRepository mBoardRepository;
	private final PSURepository psuRepository;
	private final RAMRepository ramRepository;
	
	
	//나중에 견적 사이트에서 추가해줄 것들
	public Optional<CPU> getCpu(int id) {
	    return this.cpuRepository.findById(id);
	}

	public Optional<ComCase> getComCase(int id) {
	    return this.comCaseRepository.findById(id);
	}

	public Optional<Disk> getDisk(int id) {
	    return this.diskRepository.findById(id);
	}

	public Optional<GPU> getGPU(int id) {
	    return this.gpuRepository.findById(id);
	}

	public Optional<MBoard> getMBoard(int id) {
	    return this.mBoardRepository.findById(id);
	}

	public Optional<PSU> getPSU(int id) {
	    return this.psuRepository.findById(id);
	}

	public Optional<RAM> getRAM(int id) {
	    return this.ramRepository.findById(id);
	}

	
	
	
	//-------------------------------------------------------------
	//극 초반 간단하게 forge만 생성
	public Forge create() {
		Forge forge = new Forge();
		return forge;
	}
	
	//세이브하는 메서드
	public void save() {
		
	}
	
	
	//만들어진 forge테이블에서 가져오기
	public Optional<Forge> getForge(int id) {
		return this.forgeRepository.findById(id);
	}
}
