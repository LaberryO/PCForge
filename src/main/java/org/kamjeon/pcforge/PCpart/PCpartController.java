package org.kamjeon.pcforge.PCpart;


import java.util.List;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class PCpartController {
	private final PCpartService pcPartService;

	@GetMapping("/main")
    public String main(Model model) {
		List<BaseProduct> base = this.pcPartService.getSearchList(0, "", "ALL", 30);
		List<CPU> cpu = this.pcPartService.getSearchList(0, "", "CPU", 10);
		List<ComCase> comcase = this.pcPartService.getSearchList(0, "", "COMCASE", 10);
		List<Disk> disk = this.pcPartService.getSearchList(0, "", "DISK", 10);
		List<GPU> gpu = this.pcPartService.getSearchList(0, "", "GPU", 10);
		List<MBoard> mBoard = this.pcPartService.getSearchList(0, "", "MBOARD", 10);
		List<PSU> psu = this.pcPartService.getSearchList(0, "", "PSU", 10);
		List<RAM> ram = this.pcPartService.getSearchList(0, "", "RAM", 10);
		
		model.addAttribute("productGroups", base);
		model.addAttribute("cpuList", cpu);
		model.addAttribute("gpuList",gpu );
		model.addAttribute("ramList", ram);
		model.addAttribute("diskList",disk );
		model.addAttribute("mboardList", mBoard);
		model.addAttribute("psuList", psu);
		model.addAttribute("comcaseList", comcase);
		
		model.addAttribute("miniCarouselImages", base);
		model.addAttribute("topCarouselImages", base);
		
		System.out.println("Base 갯수" + base.size());
		System.out.println("CPU 갯수: " + cpu.size());
		System.out.println("ComCase 갯수: " + comcase.size());
		System.out.println("Disk 갯수: " + disk.size());
		System.out.println("GPU 갯수: " + gpu.size());
		System.out.println("MBoard 갯수: " + mBoard.size());
		System.out.println("PSU 갯수: " + psu.size());
		System.out.println("RAM 갯수: " + ram.size());


		return "shop"; // 이동 
	}
	
}
