package org.kamjeon.pcforge.PCpart;

import java.util.ArrayList;
import java.util.List;

import org.kamjeon.pcforge.Forge.ForgeRepository;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PCpartService {
	private final PCpartRepository pcPartRepository;
	private final ComCaseRepository comCaseRepository;
	private final CPURepository cpuRepository;
	private final DiskRepository diskRepository;
	private final GPURepository gpuRepository;
	private final MBoardRepository mBoardRepository;
	private final PSURepository psuRepository;
	private final RAMRepository ramRepository;

//	public Page<PCParts> getList(int page, String kw, String searchType) {
//		List<Sort.Order> sorts = new ArrayList<>();
//		sorts.add(Sort.Order.desc("id"));
//		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
//
//		Specification<PCParts> spec = Specification.where(null);
//
//		switch (searchType) {
//		case "COMCASE":
//			spec = searchComCase(kw);
//			break;
//		case "CPU":
//			spec = searchCPU(kw);
//			break;
//		case "DISK":
//			spec = searchDisk(kw);
//			break;
//		case "GPU":
//			spec = searchGPU(kw);
//			break;
//		case "MBOARD":
//			spec = searchMBoard(kw);
//			break;
//		case "PSU":
//			spec = searchPSU(kw);
//			break;
//		case "RAM":
//			spec = searchRAM(kw);
//			break;
//		case "COMPANY":
//			spec = searchCompany(kw);
//			break;
//		default:
//			spec = searchAll(kw);
//			break;
//		}
//		return this.pcPartRepository.findAll(spec, pageable);
//	}
//
//	private Specification<PCParts> searchAll(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			query.distinct(true);
//
//			// 각 테이블 조인
//			Join<PCParts, ComCase> comCase = p.join("comCase", JoinType.LEFT);
//			Join<PCParts, CPU> cpu = p.join("cpu", JoinType.LEFT);
//			Join<PCParts, Disk> disk = p.join("disk", JoinType.LEFT);
//			Join<PCParts, GPU> gpu = p.join("gpu", JoinType.LEFT);
//			Join<PCParts, MBoard> mBoard = p.join("mBoard", JoinType.LEFT);
//			Join<PCParts, PSU> psu = p.join("psu", JoinType.LEFT);
//			Join<PCParts, RAM> ram = p.join("ram", JoinType.LEFT);
//			Join<PCParts, Company> company = p.join("company", JoinType.LEFT);
//
//			// 검색 조건
//			return cb.or(
//					// cb.like(p.get("id").as(String.class), "%" + kw + "%"), // ID 검색 필요없을 듯
//					cb.like(comCase.get("name"), "%" + kw + "%"), // 케이스 검색
//					cb.like(cpu.get("name"), "%" + kw + "%"), // CPU 검색
//					cb.like(disk.get("name"), "%" + kw + "%"), // 디스크 검색
//					cb.like(gpu.get("name"), "%" + kw + "%"), // GPU 검색
//					cb.like(mBoard.get("name"), "%" + kw + "%"), // 메인보드 검색
//					cb.like(psu.get("name"), "%" + kw + "%"), // 파워 검색
//					cb.like(ram.get("name"), "%" + kw + "%"), // RAM 검색
//					cb.like(company.get("name"), "%" + kw + "%") // 제조사 검색
//			);
//		};
//	}

//	private Specification<PCParts> searchComCase(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			query.distinct(true);
//			Join<PCParts, ComCase> comCase = p.join("comCase", JoinType.LEFT);
//			return cb.like(comCase.get("name"), "%" + kw + "%");
//		};
//	}
//
//	private Specification<PCParts> searchCPU(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			Join<PCParts, CPU> cpu = p.join("cpu", JoinType.LEFT);
//			return cb.like(cpu.get("name"), "%" + kw + "%");
//		};
//	}
//
//	private Specification<PCParts> searchDisk(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			Join<PCParts, Disk> disk = p.join("disk", JoinType.LEFT);
//			return cb.like(disk.get("name"), "%" + kw + "%");
//		};
//	}
//
//	private Specification<PCParts> searchGPU(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			Join<PCParts, GPU> gpu = p.join("gpu", JoinType.LEFT);
//			return cb.like(gpu.get("name"), "%" + kw + "%");
//		};
//	}
//
//	private Specification<PCParts> searchMBoard(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			Join<PCParts, MBoard> mBoard = p.join("mBoard", JoinType.LEFT);
//			return cb.like(mBoard.get("name"), "%" + kw + "%");
//		};
//	}
//
//	private Specification<PCParts> searchPSU(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			Join<PCParts, PSU> psu = p.join("psu", JoinType.LEFT);
//			return cb.like(psu.get("name"), "%" + kw + "%");
//		};
//	}
//
//	private Specification<PCParts> searchRAM(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			Join<PCParts, RAM> ram = p.join("ram", JoinType.LEFT);
//			return cb.like(ram.get("name"), "%" + kw + "%");
//		};
//	}
//
//	private Specification<PCParts> searchCompany(String kw) {
//		return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
//			Join<PCParts, Company> company = p.join("company", JoinType.LEFT);
//			return cb.like(company.get("name"), "%" + kw + "%");
//		};
//	}

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
				Join<CPU, Company> company = cpu.join("makeCompany", JoinType.LEFT);
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
				Join<ComCase, Company> company = comCase.join("makeCompany", JoinType.LEFT);
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
				Join<Disk, Company> company = disk.join("makeCompany", JoinType.LEFT);
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
				Join<GPU, Company> company = gpu.join("makeCompany", JoinType.LEFT);
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
				Join<MBoard, Company> company = mboard.join("makeCompany", JoinType.LEFT);
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
				Join<PSU, Company> company = psu.join("makeCompany", JoinType.LEFT);
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
				Join<RAM, Company> company = ram.join("makeCompany", JoinType.LEFT);
				return cb.or(cb.like(ram.get("name"), "%" + kw + "%"), cb.like(company.get("name"), "%" + kw + "%"));
			}
		};
	}

}
