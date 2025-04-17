package org.kamjeon.pcforge.Forge;

import java.util.List;
import java.util.Optional;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.CPU.CPURepository;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.ComCase.ComCaseRepository;
import org.kamjeon.pcforge.PCpart.Company.Company;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.servlet.http.HttpSession;
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
	
    public Forge getForgeForSession(HttpSession session) {
        // 세션 ID 가져오기
        String sessionId = session.getId();

        // 세션 ID를 기반으로 Forge 가져오기
        Optional<Forge> optionalForge = forgeRepository.findBySessionId(sessionId);
        if (optionalForge.isPresent()) {
            return optionalForge.get();
        } else {
            // Forge가 없으면 새로 생성
            Forge forge = new Forge();
            forge.setSessionId(sessionId); // 세션 ID 저장
            forgeRepository.save(forge);
            return forge;
        }
    }

	public void addOrUpdatePart(String partType, Integer partId, HttpSession session) {
		Forge forge = getForgeForSession(session);

		// 부품을 가져와 Forge에 저장
		switch (partType) {
		case "cpu":
			CPU cpu = cpuRepository.findById(partId).orElseThrow(() -> new IllegalArgumentException("Invalid CPU ID"));
			forge.setCpu(cpu);
			break;
		case "ram":
			RAM ram = ramRepository.findById(partId).orElseThrow(() -> new IllegalArgumentException("Invalid RAM ID"));
			forge.setRam(ram);
			break;
		case "gpu":
			GPU gpu = gpuRepository.findById(partId).orElseThrow(() -> new IllegalArgumentException("Invalid GPU ID"));
			forge.setGpu(gpu);
			break;
		case "mboard":
			MBoard mboard = mBoardRepository.findById(partId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Motherboard ID"));
			forge.setMboard(mboard);
			break;
		case "disk":
			Disk disk = diskRepository.findById(partId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Disk ID"));
			forge.setDisk(disk);
			break;
		case "psu":
			PSU psu = psuRepository.findById(partId).orElseThrow(() -> new IllegalArgumentException("Invalid PSU ID"));
			forge.setPsu(psu);
			break;
		case "comcase":
			ComCase comCase = comCaseRepository.findById(partId)
					.orElseThrow(() -> new IllegalArgumentException("Invalid Case ID"));
			forge.setComCase(comCase);
			break;
		}

		// Forge 저장
		forgeRepository.save(forge);
	}

	// 나중에 견적 사이트에서 추가해줄 것들
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

	// -------------------------------------------------------------
	// 극 초반 간단하게 forge만 생성
	public Forge create() {
		Forge forge = new Forge();
		return forge;
	}

	// 세이브하는 메서드
	public void save(Forge forge) {
		this.forgeRepository.save(forge);
	}

	// 만들어진 forge테이블에서 가져오기
	public Optional<Forge> getForge(int id) {
		return this.forgeRepository.findById(id);
	}

	// 단일 검색 쿼리

	public List<CPU> getCPUList(String kw) {
		Specification<CPU> spec = searchCPU(kw);
		return this.cpuRepository.findAll(spec);
	}

	public List<ComCase> getComCaseList(String kw) {
		Specification<ComCase> spec = searchComCase(kw);
		return this.comCaseRepository.findAll(spec);
	}

	public List<Disk> getDiskList(String kw) {
		Specification<Disk> spec = searchDisk(kw);
		return this.diskRepository.findAll(spec);
	}

	public List<GPU> getGPUList(String kw) {
		Specification<GPU> spec = searchGPU(kw);
		return this.gpuRepository.findAll(spec);
	}

	public List<MBoard> getMBoardList(String kw) {
		Specification<MBoard> spec = searchMBoard(kw);
		return this.mBoardRepository.findAll(spec);
	}

	public List<PSU> getPSUList(String kw) {
		Specification<PSU> spec = searchPSU(kw);
		return this.psuRepository.findAll(spec);
	}

	public List<RAM> getRAMList(String kw) {
		Specification<RAM> spec = searchRAM(kw);
		return this.ramRepository.findAll(spec);
	}

	public List<CPU> getCPUList() {
		return this.cpuRepository.findAll();
	}

	public List<ComCase> getComCaseList() {
		return this.comCaseRepository.findAll();
	}

	public List<Disk> getDiskList() {
		return this.diskRepository.findAll();
	}

	public List<GPU> getGPUList() {
		return this.gpuRepository.findAll();
	}

	public List<MBoard> getMBoardList() {
		return this.mBoardRepository.findAll();
	}

	public List<PSU> getPSUList() {
		return this.psuRepository.findAll();
	}

	public List<RAM> getRAMList() {
		return this.ramRepository.findAll();
	}

	private Specification<CPU> searchCPU(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<CPU> cpu, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<CPU, Company> company = cpu.join("myCom", JoinType.LEFT);
				return cb.or(cb.like(cpu.get("name"), "%" + kw + "%"), cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

	private Specification<ComCase> searchComCase(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<ComCase> comCase, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<ComCase, Company> company = comCase.join("myCom", JoinType.LEFT);
				return cb.or(cb.like(comCase.get("name"), "%" + kw + "%"),
						cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

	private Specification<Disk> searchDisk(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<Disk> disk, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<Disk, Company> company = disk.join("myCom", JoinType.LEFT);
				return cb.or(cb.like(disk.get("name"), "%" + kw + "%"), cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

	private Specification<GPU> searchGPU(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<GPU> gpu, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<GPU, Company> company = gpu.join("myCom", JoinType.LEFT);
				return cb.or(cb.like(gpu.get("name"), "%" + kw + "%"), cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

	private Specification<MBoard> searchMBoard(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<MBoard> mboard, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<MBoard, Company> company = mboard.join("myCom", JoinType.LEFT);
				return cb.or(cb.like(mboard.get("name"), "%" + kw + "%"), cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

	private Specification<PSU> searchPSU(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<PSU> psu, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<PSU, Company> company = psu.join("myCom", JoinType.LEFT);
				return cb.or(cb.like(psu.get("name"), "%" + kw + "%"), cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

	private Specification<RAM> searchRAM(String kw) {
		return new Specification<>() {
			private static final long serialVersionUID = 1L;

			@Override
			public Predicate toPredicate(Root<RAM> ram, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<RAM, Company> company = ram.join("myCom", JoinType.LEFT);
				return cb.or(cb.like(ram.get("name"), "%" + kw + "%"), cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

}
