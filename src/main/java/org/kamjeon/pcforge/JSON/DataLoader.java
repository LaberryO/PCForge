package org.kamjeon.pcforge.JSON;

import java.io.IOException;
import java.util.List;

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
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		 Resource company = resolver.getResource("classpath:json/Company.json");
		    if (company != null) {
		        List<Company> companyData = objectMapper.readValue(company.getInputStream(), new TypeReference<List<Company>>() {});
		        companyRp.saveAll(companyData); 
		    }

		  try {
	            Resource[] resources = resolver.getResources("classpath:json/*.json");

	            for (Resource res : resources) {
	                String fileName = res.getFilename();
	                if (fileName == null) continue;
	                if(fileName.equalsIgnoreCase("CPU.json")) continue;
	                if(fileName.equalsIgnoreCase("Company.json")) continue;

					/*
					 * if (fileName.equalsIgnoreCase("cpu.json")) { List<CPU> cpuData =
					 * objectMapper.readValue(res.getInputStream(), new TypeReference<List<CPU>>()
					 * {}); cpuRp.saveAll(cpuData); }
					 */ if (fileName.equalsIgnoreCase("Disk.json")) {
	                    List<Disk> diskData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<Disk>>() {});
	                    diskRp.saveAll(diskData);
	                } else if (fileName.equalsIgnoreCase("GPU.json")) {
	                    List<GPU> gpuData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<GPU>>() {});
	                    gpuRp.saveAll(gpuData);
	                } else if (fileName.equalsIgnoreCase("MBoard.json")) {
	                    List<MBoard> mBoardData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<MBoard>>() {});
	                    mBoardRp.saveAll(mBoardData);
	                } else if (fileName.equalsIgnoreCase("PSU.json")) {
	                    List<PSU> psuData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<PSU>>() {});
	                    psuRp.saveAll(psuData);
	                } else if (fileName.equalsIgnoreCase("Ram.json")) {
	                    List<RAM> ramData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<RAM>>() {});
	                    ramRp.saveAll(ramData);
	                }else if(fileName.equalsIgnoreCase("ComCase")) {
	                	 List<ComCase> comData = objectMapper.readValue(res.getInputStream(), new TypeReference<List<ComCase>>() {});
		                 comRp.saveAll(comData);
	                }
	                else {
	                    System.out.println(" 지원하지 않는 JSON 파일이다 시발아: " + fileName);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            throw new RuntimeException("JSON 파일 로딩 오류");
	        }
	}
}
