package org.kamjeon.pcforge.PCpart;

import java.util.ArrayList;
import java.util.List;

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
    private final ComCaseRepository comcaseRepository;
    private final CPURepository cpuRepository;
    private final DiskRepository diskRepository;
    private final GPURepository gpuRepository;
    private final MBoardRepository mBoardRepository;
    private final PSURepository psuRepository;
    private final RAMRepository ramRepository;

    @SuppressWarnings("unchecked")
    public <T> Page<T> getList(int page, String kw, String searchType, int num) {
    	  
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));  // 기본 정렬 기준을 설정해줌

        Pageable pageable = PageRequest.of(page, num, Sort.by(sorts));  // 페이지와 정렬 설정

        switch (searchType) {
            case "COMCASE":
                Specification<ComCase> comCaseSpec = searchComCase(kw);
                return (Page<T>) comcaseRepository.findAll(comCaseSpec, pageable);

            case "CPU":
                Specification<CPU> cpuSpec = searchCPU(kw);
                return (Page<T>) cpuRepository.findAll(cpuSpec, pageable);

            case "DISK":
                Specification<Disk> diskSpec = searchDisk(kw);
                return (Page<T>) diskRepository.findAll(diskSpec, pageable);

            case "GPU":
                Specification<GPU> gpuSpec = searchGPU(kw);
                return (Page<T>) gpuRepository.findAll(gpuSpec, pageable);

            case "MBOARD":
                Specification<MBoard> mBoardSpec = searchMBoard(kw);
                return (Page<T>) mBoardRepository.findAll(mBoardSpec, pageable);

            case "PSU":
                Specification<PSU> psuSpec = searchPSU(kw);
                return (Page<T>) psuRepository.findAll(psuSpec, pageable);

            case "RAM":
                Specification<RAM> ramSpec = searchRAM(kw);
                return (Page<T>) ramRepository.findAll(ramSpec, pageable);

            default:

                Specification<PCParts> pcPartsSpec = searchAll(kw);
                return (Page<T>) pcPartRepository.findAll(pcPartsSpec, pageable);
        }
    }
    

    private Specification<PCParts> searchAll(String kw) {
        return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            System.out.println("여기 실행하는 중");
            query.distinct(true);
            Join<PCParts, BaseProduct> baseProductJoin = p.join("baseProduct", JoinType.INNER);

            // CPU의 name이 검색어를 포함하는 조건
            return cb.like(baseProductJoin.get("name"), "%" + kw + "%");
        };
    }
    private Specification<ComCase> searchComCase(String kw) {
        return (Root<ComCase> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<ComCase, PCParts> comCase = p.join("pcPart", JoinType.INNER);
            return cb.like(comCase.get("name"), "%" + kw + "%");
        };
    }

    private Specification<CPU> searchCPU(String kw) {
        return (Root<CPU> cpu, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            System.out.println("cpu 실행하는 중");
            query.distinct(true);
            Join<CPU, PCParts> pcParts = cpu.join("pcPart", JoinType.INNER);

            // CPU의 name이 검색어를 포함하는 조건
            return cb.like(cpu.get("name"), "%" + kw + "%");
        };
    }

    private Specification<Disk> searchDisk(String kw) {
        return (Root<Disk> disk, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<Disk, PCParts> pcParts = disk.join("pcPart", JoinType.INNER);
            return cb.like(disk.get("name"), "%" + kw + "%");
        };
    }

    private Specification<GPU> searchGPU(String kw) {
        return (Root<GPU> gpu, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<GPU, PCParts> pcParts = gpu.join("pcPart", JoinType.INNER);
            return cb.like(gpu.get("name"), "%" + kw + "%");
        };
    }

    private Specification<MBoard> searchMBoard(String kw) {
        return (Root<MBoard> mBoard, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<MBoard, PCParts> pcParts = mBoard.join("pcPart", JoinType.INNER);
            return cb.like(mBoard.get("name"), "%" + kw + "%");
        };
    }

    private Specification<PSU> searchPSU(String kw) {
        return (Root<PSU> psu, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<PSU, PCParts> pcParts = psu.join("pcPart", JoinType.INNER);
            return cb.like(psu.get("name"), "%" + kw + "%");
        };
    }

    private Specification<RAM> searchRAM(String kw) {
        return (Root<RAM> ram, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<RAM, PCParts> pcParts = ram.join("pcPart", JoinType.INNER);
            return cb.like(ram.get("name"), "%" + kw + "%");
        };
    }

    private Specification<Company> searchCompany(String kw) {
        return (Root<Company> company, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<Company, PCParts> pcParts = company.join("pcPart", JoinType.INNER);
            return cb.like(company.get("name"), "%" + kw + "%");
        };
    }
}
