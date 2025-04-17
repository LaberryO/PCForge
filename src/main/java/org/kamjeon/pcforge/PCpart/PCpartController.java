package org.kamjeon.pcforge.PCpart;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.kamjeon.pcforge.PCpart.ComCase.ComCase;
import org.kamjeon.pcforge.PCpart.Company.Company;
import org.kamjeon.pcforge.PCpart.Company.CompanyRepository;
import org.kamjeon.pcforge.PCpart.Disk.Disk;
import org.kamjeon.pcforge.PCpart.GPU.GPU;
import org.kamjeon.pcforge.PCpart.MBoard.MBoard;
import org.kamjeon.pcforge.PCpart.PSU.PSU;
import org.kamjeon.pcforge.PCpart.RAM.RAM;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class PCpartController {
	private final PCpartService pcPartService;
	private final BaseProductRepository baseRep;
	private final CompanyRepository comRep;

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
		
		 List<List<BaseProduct>> groupedImages = new ArrayList<>();
		 
		    for (int i = 0; i < base.size(); i += 3) {
		        int end = Math.min(i + 3, base.size());
		        groupedImages.add(base.subList(i, end));
		    }
		model.addAttribute("productGroups", base);
		model.addAttribute("cpuList", cpu);
		model.addAttribute("gpuList",gpu );
		model.addAttribute("ramList", ram);
		model.addAttribute("diskList",disk );
		model.addAttribute("mboardList", mBoard);
		model.addAttribute("psuList", psu);
		model.addAttribute("comcaseList", comcase);
		
		model.addAttribute("miniCarouselImages", base);
		model.addAttribute("topCarouselImages", groupedImages);

		return "shop"; // 이동 
	}
	
	@GetMapping("/goShop/{id}")
	public String goShop(Model model,@PathVariable("id") int id) {
		Optional<BaseProduct> base = this.baseRep.findById(id);
		if(!base.isPresent())
			return null;
		BaseProduct product = base.get();
		
		Optional<Company> com = this.comRep.findById(product.getMakeCompany());
		if(!com.isPresent())
			return null;
		Company company = com.get();
		
		System.out.println(product.getFileName());
		model.addAttribute("product", product);
		model.addAttribute("companyName", company.getName());
		
		return "shop_fragment";
	}
	
	@GetMapping("/filter/{search}")
	public String filter(Model model, @PathVariable("search") String type) {
		switch(type) {
		   case "COMCASE":
		      List<ComCase> comList = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", comList);
		      break;
		   case "CPU":
		      List<CPU> cpu = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", cpu);
		      break;
		   case "DISK":
		      List<Disk> disk = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", disk);
		      break;
		   case "GPU":
		      List<GPU> gpu = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", gpu);
		      break;
		   case "MBOARD":
		      List<MBoard> mboard = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", mboard);
		      break;
		   case "PSU":
		      List<PSU> psu = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", psu);
		      break;
		   case "RAM":
		      List<RAM> ram = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", ram);
		      break;
		   default:
		      List<BaseProduct> base = this.pcPartService.getSearchList(0, "", type, 10);
		      model.addAttribute("productList", base);
		      System.out.println(base.size());
		      break;
		}
		
		return "redirect:/shop/main#productList";
	}
}
