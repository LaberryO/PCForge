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
   
    public <T> List<T> getSearchList(int page, String kw, String searchType, int num){
    	List<T> newList = new ArrayList<T>();
    	
   	 Page<PCParts> paging = getList(page, kw, searchType , num);
   	 for (PCParts pcPart : paging.getContent()) {
         switch (searchType) {
             case "CPU":
                 if (pcPart.getCpu() != null) {
                     newList.add((T) pcPart.getCpu());
                 }
                 break;
             case "COMCASE":
                 if (pcPart.getComcase() != null) {
                     newList.add((T) pcPart.getComcase());
                 }
                 break;
             case "DISK":
                 if (pcPart.getDisk() != null) {
                     newList.add((T) pcPart.getDisk());
                 }
                 break;
             case "GPU":
                 if (pcPart.getGpu() != null) {
                     newList.add((T) pcPart.getGpu());
                 }
                 break;
             case "MBOARD":
                 if (pcPart.getMBoard() != null) {
                     newList.add((T) pcPart.getMBoard());
                 }
                 break;
             case "PSU":
                 if (pcPart.getPsu() != null) {
                     newList.add((T) pcPart.getPsu());
                 }
                 break;
             case "RAM":
                 if (pcPart.getRam() != null) {
                     newList.add((T) pcPart.getRam());
                 }
                 break;
             default:
            	   if (pcPart.getBaseProduct() != null) {
                       newList.add((T) pcPart.getBaseProduct());
                   }
                 break;
         }
     }
   	 
     return newList;
    }
    @SuppressWarnings("unchecked")
    public <T> Page<T> getList(int page, String kw, String searchType, int num) {
    	  
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));  // 기본 정렬 기준을 설정해줌

        Pageable pageable = PageRequest.of(page, num, Sort.by(sorts));  // 페이지와 정렬 설정

        switch (searchType) {
            case "COMCASE":
                Specification<PCParts> comCaseSpec = searchComCase(kw);
                return (Page<T>) pcPartRepository.findAll(comCaseSpec, pageable);

            case "CPU":
                Specification<PCParts> cpuSpec = searchCPU(kw);
                return (Page<T>) pcPartRepository.findAll(cpuSpec, pageable);

            case "DISK":
                Specification<PCParts> diskSpec = searchDisk(kw);
                return (Page<T>) pcPartRepository.findAll(diskSpec, pageable);

            case "GPU":
                Specification<PCParts> gpuSpec = searchGPU(kw);
                return (Page<T>) pcPartRepository.findAll(gpuSpec, pageable);

            case "MBOARD":
                Specification<PCParts> mBoardSpec = searchMBoard(kw);
                return (Page<T>) pcPartRepository.findAll(mBoardSpec, pageable);

            case "PSU":
                Specification<PCParts> psuSpec = searchPSU(kw);
                return (Page<T>) pcPartRepository.findAll(psuSpec, pageable);

            case "RAM":
                Specification<PCParts> ramSpec = searchRAM(kw);
                return (Page<T>) pcPartRepository.findAll(ramSpec, pageable);

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
    private Specification<PCParts> searchComCase(String kw) {
        return (Root<PCParts> p, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<ComCase, PCParts> comCase = p.join("comcase", JoinType.INNER);
            return cb.like(comCase.get("name"), "%" + kw + "%");
        };
    }

    private Specification<PCParts> searchCPU(String kw) {
        return (Root<PCParts> cpu, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            System.out.println("cpu 실행하는 중");
            query.distinct(true);
            Join<CPU, PCParts> pcParts = cpu.join("cpu", JoinType.INNER);

            // CPU의 name이 검색어를 포함하는 조건
            return cb.like(pcParts.get("name"), "%" + kw + "%");
        };
    }

    private Specification<PCParts> searchDisk(String kw) {
        return (Root<PCParts> disk, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<Disk, PCParts> pcParts = disk.join("disk", JoinType.INNER);
            return cb.like(pcParts.get("name"), "%" + kw + "%");
        };
    }

    private Specification<PCParts> searchGPU(String kw) {
        return (Root<PCParts> gpu, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<GPU, PCParts> pcParts = gpu.join("gpu", JoinType.INNER);
            return cb.like(pcParts.get("name"), "%" + kw + "%");
        };
    }

    private Specification<PCParts> searchMBoard(String kw) {
        return (Root<PCParts> mBoard, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<MBoard, PCParts> pcParts = mBoard.join("mBoard", JoinType.INNER);
            return cb.like(pcParts.get("name"), "%" + kw + "%");
        };
    }

    private Specification<PCParts> searchPSU(String kw) {
        return (Root<PCParts> psu, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<PSU, PCParts> pcParts = psu.join("psu", JoinType.INNER);
            return cb.like(pcParts.get("name"), "%" + kw + "%");
        };
    }

    private Specification<PCParts> searchRAM(String kw) {
        return (Root<PCParts> ram, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
            query.distinct(true);
            Join<RAM, PCParts> pcParts = ram.join("ram", JoinType.INNER);
            return cb.like(pcParts.get("name"), "%" + kw + "%");
        };
    }

}
