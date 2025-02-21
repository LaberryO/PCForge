package org.kamjeon.pcforge.PCpart;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Company.Company;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PCpartService {
	private final PCpartRepository pcPartRepository;
	
	
	private Specification<PCParts> searchAll(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        query.distinct(true); // 중복 제거
	        
	        // 각 테이블 조인
	        Join<PCParts, ComCase> comCase = p.join("comCase", JoinType.LEFT);
	        Join<PCParts, CPU> cpu = p.join("cpu", JoinType.LEFT);
	        Join<PCParts, Disk> disk = p.join("disk", JoinType.LEFT);
	        Join<PCParts, GPU> gpu = p.join("gpu", JoinType.LEFT);
	        Join<PCParts, MBoard> mBoard = p.join("mBoard", JoinType.LEFT);
	        Join<PCParts, PSU> psu = p.join("psu", JoinType.LEFT);
	        Join<PCParts, RAM> ram = p.join("ram", JoinType.LEFT);
	        Join<PCParts, Company> company = p.join("company", JoinType.LEFT);

	        // 검색 조건
	        return cb.or(
	            //cb.like(p.get("id").as(String.class), "%" + kw + "%"), // ID 검색 필요없을 듯
	            cb.like(comCase.get("name"), "%" + kw + "%"), // 케이스 검색
	            cb.like(cpu.get("name"), "%" + kw + "%"), // CPU 검색
	            cb.like(disk.get("name"), "%" + kw + "%"), // 디스크 검색
	            cb.like(gpu.get("name"), "%" + kw + "%"), // GPU 검색
	            cb.like(mBoard.get("name"), "%" + kw + "%"), // 메인보드 검색
	            cb.like(psu.get("name"), "%" + kw + "%"), // 파워 검색
	            cb.like(ram.get("name"), "%" + kw + "%"), // RAM 검색
	            cb.like(company.get("name"), "%" + kw + "%") // 제조사 검색
	        );
	    };
	}
	
	private Specification<PCParts> searchComCase(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        query.distinct(true);
	        Join<PCParts, ComCase> comCase = p.join("comCase", JoinType.LEFT);
	        return cb.like(comCase.get("name"), "%" + kw + "%");
	    };
	}

	private Specification<PCParts> searchCPU(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Join<PCParts, CPU> cpu = p.join("cpu", JoinType.LEFT);
	        return cb.like(cpu.get("name"), "%" + kw + "%");
	    };
	}

	private Specification<PCParts> searchDisk(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Join<PCParts, Disk> disk = p.join("disk", JoinType.LEFT);
	        return cb.like(disk.get("name"), "%" + kw + "%");
	    };
	}

	private Specification<PCParts> searchGPU(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Join<PCParts, GPU> gpu = p.join("gpu", JoinType.LEFT);
	        return cb.like(gpu.get("name"), "%" + kw + "%");
	    };
	}

	private Specification<PCParts> searchMBoard(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Join<PCParts, MBoard> mBoard = p.join("mBoard", JoinType.LEFT);
	        return cb.like(mBoard.get("name"), "%" + kw + "%");
	    };
	}

	private Specification<PCParts> searchPSU(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Join<PCParts, PSU> psu = p.join("psu", JoinType.LEFT);
	        return cb.like(psu.get("name"), "%" + kw + "%");
	    };
	}

	private Specification<PCParts> searchRAM(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Join<PCParts, RAM> ram = p.join("ram", JoinType.LEFT);
	        return cb.like(ram.get("name"), "%" + kw + "%");
	    };
	}

	private Specification<PCParts> searchCompany(String kw) {
	    return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
	        Join<PCParts, Company> company = p.join("company", JoinType.LEFT);
	        return cb.like(company.get("name"), "%" + kw + "%");
	    };
	}
}
