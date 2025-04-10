package org.kamjeon.pcforge.JSON;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
		/*
		 * if (pcRp.count() > 0) { // 이미 데이터가 존재하면 로딩 중단
		 * System.out.println("데이터가 이미 존재합니다. 로딩을 건너뜁니다."); return; }
		 */
		/*
		 * PCParts pcpart = new PCParts(); this.pcRp.save(pcpart);
		 * System.out.println("새로운 PCParts 객체 생성");
		 */

	    PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

	    // Company.json 로드 (회사 데이터 확인)
	    Resource company = resolver.getResource("classpath:json/Company.json");
	    if (company.exists() && companyRp.count() == 0) { // Company 데이터가 없을 경우만 추가
	        List<Company> companyData = objectMapper.readValue(company.getInputStream(), new TypeReference<List<Company>>() {});
			/*
			 * for (Company c : companyData) { PCParts pcParts = new PCParts(); pcParts =
			 * pcRp.save(pcParts); c.setPcPart(pcParts); }
			 */
	        companyRp.saveAll(companyData);
	        System.out.println("회사 데이터 저장 완료");
	    }

	    try {
	        System.out.println("2차 트랜잭션 실행");
	        Resource[] resources = resolver.getResources("classpath:json/*.json");

	        for (Resource res : resources) {
	            String fileName = res.getFilename();
	            if (fileName == null) continue;

	            if (fileName.equalsIgnoreCase("Disk.json") && diskRp.count() == 0) {
	                List<Disk> diskData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<Disk>>() {});
	                for (Disk disk : diskData) {
	                	 PCParts pcParts = new PCParts();
	                	    pcParts = pcRp.save(pcParts);

	                	    disk.setPcPart(pcParts); 

	                    Optional<Company> com = this.companyRp.findById(disk.getMakeCompany());
	                    
	                }
	                diskRp.saveAll(diskData);
	                System.out.println("디스크 데이터 저장 완료");
	            } 
	            else if (fileName.equalsIgnoreCase("CPU.json") && cpuRp.count() == 0) {
	                List<CPU> cpuData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<CPU>>() {});
	                for (CPU cpu : cpuData) {
	                 	 PCParts pcParts = new PCParts();
	                	    pcParts = pcRp.save(pcParts);

	                	    cpu.setPcPart(pcParts); 

	                    Optional<Company> com = this.companyRp.findById(cpu.getMakeCompany());
	                   
	                }
	                cpuRp.saveAll(cpuData);
	                System.out.println("CPU 데이터 저장 완료");
	            }
	            else if (fileName.equalsIgnoreCase("GPU.json") && gpuRp.count() == 0) {
	                List<GPU> gpuData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<GPU>>() {});
	                for (GPU gpu : gpuData) {
	                	 PCParts pcParts = new PCParts();
	                	    pcParts = pcRp.save(pcParts);

	                	    gpu.setPcPart(pcParts); 
	                    Optional<Company> com = this.companyRp.findById(gpu.getMakeCompany());
	                   
	                }
	                gpuRp.saveAll(gpuData);
	                System.out.println("GPU 데이터 저장 완료");
	            }
	            else if (fileName.equalsIgnoreCase("MBoard.json") && mBoardRp.count() == 0) {
	                List<MBoard> mBoardData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<MBoard>>() {});
	                for (MBoard mBoard : mBoardData) {
	                	 PCParts pcParts = new PCParts();
	                	    pcParts = pcRp.save(pcParts);

	                	    mBoard.setPcPart(pcParts); 
	                    Optional<Company> com = this.companyRp.findById(mBoard.getMakeCompany());
	           
	                }
	                mBoardRp.saveAll(mBoardData);
	                System.out.println("MBoard 데이터 저장 완료");
	            }
	            else if (fileName.equalsIgnoreCase("PSU.json") && psuRp.count() == 0) {
	                List<PSU> psuData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<PSU>>() {});
	                for (PSU psu : psuData) {
	                	 PCParts pcParts = new PCParts();
	                	    pcParts = pcRp.save(pcParts);

	                	    psu.setPcPart(pcParts); 
	                    Optional<Company> com = this.companyRp.findById(psu.getMakeCompany());
	              
	                }
	                psuRp.saveAll(psuData);
	                System.out.println("PSU 데이터 저장 완료");
	            }
	            else if (fileName.equalsIgnoreCase("Ram.json") && ramRp.count() == 0) {
	                List<RAM> ramData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<RAM>>() {});
	                for (RAM ram : ramData) {
	                	 PCParts pcParts = new PCParts();
	                	    pcParts = pcRp.save(pcParts);

	                	    ram.setPcPart(pcParts); 
	                    Optional<Company> com = this.companyRp.findById(ram.getMakeCompany());
	             
	                }
	                ramRp.saveAll(ramData);
	                System.out.println("RAM 데이터 저장 완료");
	            }
	            else if (fileName.equalsIgnoreCase("ComCase.json") && comRp.count() == 0) {
	                List<ComCase> comData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<ComCase>>() {});
	                for (ComCase comCase : comData) {
	                	 PCParts pcParts = new PCParts();
	                	    pcParts = pcRp.save(pcParts);

	                	    comCase.setPcPart(pcParts); 
	                    Optional<Company> com = this.companyRp.findById(comCase.getMakeCompany());
	           
	                }
	                comRp.saveAll(comData);
	                System.out.println("컴퓨터 케이스 데이터 저장 완료");
	            } 
	            else {
	                System.out.println("이미 데이터가 존재하거나 지원하지 않는 JSON 파일: " + fileName);
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("JSON 파일 로딩 오류");
	    }
	}


}
