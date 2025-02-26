package org.kamjeon.pcforge.JSON;

import java.io.IOException;
import java.util.List;

import org.kamjeon.pcforge.PCpart.PCParts;
import org.kamjeon.pcforge.PCpart.PCpartRepository;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.CPU.CPURepository;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.ComCase.ComCaseRepository;
import org.kamjeon.pcforge.PCpart.Company.Company;
import org.kamjeon.pcforge.PCpart.Company.CompanyRepository;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DataLoader {
	private final ObjectMapper objectMapper;
	private final PCpartRepository pcRp;
	private final ComCaseRepository comRp;
	private final CompanyRepository companyRp;
	private final CPURepository cpuRp;
	private final DiskRepository diskRp;
	private final GPURepository gpuRp;
	private final MBoardRepository mBoardRp;
	private final PSURepository psuRp;
	private final RAMRepository ramRp;
	
	
	@Transactional
	public void allDataLoder() throws IOException {
		 PCParts pcpart = pcRp.findById(1).orElse(null);  // 가장 첫 번째 PCParts를 가져옴 (또는 다른 조건으로 확인 가능)
		 	System.out.println(pcRp.findById(1));
		    if (pcpart == null) {
		        pcpart = new PCParts();
		        this.pcRp.save(pcpart); 
		        System.out.println("새로운 PCParts 객체 생성");
		    } else {
		        System.out.println("기존 PCParts 객체 사용");
		        return;
		    }

	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	    // Company.json 로드
	    Resource company = resolver.getResource("classpath:json/Company.json");
	    if (company != null) {
	    	 System.out.println("데이터 찾음");
	        List<Company> companyData = objectMapper.readValue(company.getInputStream(), new TypeReference<List<Company>>() {});
	    	 System.out.println("데이터 넣기 시작함");
	    		int num = 0;
	        for (Company c : companyData) {
	        	num++;
	        	System.out.println("반복 횟수"+num);
	        	if(pcpart == null)
	        		System.out.println("시발");
	            c.setPcPart(pcpart);  // Company와 PCParts 연결
	            System.out.println("PCPart 고유 ID"+pcpart.getPcparts_id());
	        }
	    	 System.out.println("데이터 찾기 전");
	        companyRp.saveAll(companyData); 
		    System.out.println("데이터 저장함");
	    }

	    try {
	    	System.out.println("2차 트랜잭션 실행");
	        // JSON 파일들을 순차적으로 처리
	        Resource[] resources = resolver.getResources("classpath:json/*.json");

	        for (Resource res : resources) {
	            String fileName = res.getFilename();
	            if (fileName == null) continue;
	            System.out.println(fileName);
	            if(fileName.equalsIgnoreCase("CPU.json") || fileName.equalsIgnoreCase("Company.json")) continue;

	            if (fileName.equalsIgnoreCase("Disk.json")) {
	                List<Disk> diskData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<Disk>>() {});
	                for (Disk disk : diskData) {
	                    disk.setPcPart(pcpart);  // Disk와 PCParts 연결
	                }
	                diskRp.saveAll(diskData);
	                System.out.println("디스크 완료");
	            } else if (fileName.equalsIgnoreCase("GPU.json")) {
	                List<GPU> gpuData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<GPU>>() {});
	                for (GPU gpu : gpuData) {
	                    gpu.setPcPart(pcpart);  // GPU와 PCParts 연결
	                }
	                gpuRp.saveAll(gpuData);
	                System.out.println("지피유 완료");
	            } else if (fileName.equalsIgnoreCase("MBoard.json")) {
	                List<MBoard> mBoardData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<MBoard>>() {});
	                for (MBoard mBoard : mBoardData) {
	                    mBoard.setPcPart(pcpart);  // MBoard와 PCParts 연결
	                }
	                mBoardRp.saveAll(mBoardData);
	                System.out.println("엠보드 완료");
	            } else if (fileName.equalsIgnoreCase("PSU.json")) {
	                List<PSU> psuData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<PSU>>() {});
	                for (PSU psu : psuData) {
	                    psu.setPcPart(pcpart);  // PSU와 PCParts 연결
	                }
	                psuRp.saveAll(psuData);
	                System.out.println("피시유 완료");
	            } else if (fileName.equalsIgnoreCase("Ram.json")) {
	                List<RAM> ramData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<RAM>>() {});
	                for (RAM ram : ramData) {
	                    ram.setPcPart(pcpart);  // RAM과 PCParts 연결
	                }
	                ramRp.saveAll(ramData);
	                System.out.println("렘 완료");
	            } else if (fileName.equalsIgnoreCase("ComCase.json")) {
	                List<ComCase> comData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<ComCase>>() {});
	                for (ComCase comCase : comData) {
	                    comCase.setPcPart(pcpart);  // ComCase와 PCParts 연결
	                }
	                comRp.saveAll(comData);
	                System.out.println("컴 완료");
	            } else {
	                System.out.println("지원하지 않는 JSON 파일: " + fileName);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("JSON 파일 로딩 오류");
	    }
	}

}
