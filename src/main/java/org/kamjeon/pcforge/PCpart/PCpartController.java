package org.kamjeon.pcforge.PCpart;


import java.security.Principal;
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
import org.kamjeon.pcforge.User.SiteUser;
import org.kamjeon.pcforge.User.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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
	private final UserRepository userRepository;
	
	@GetMapping("/main")
	public String main() {
		return "redirect:/shop/main/ALL";
	}

	@GetMapping("/main/{search}")
    public String main(Model model,  @PathVariable("search") String type,
    		@RequestParam(value = "kw", defaultValue = "") String kw,
			@RequestParam(value = "page", defaultValue = "0") int page) {
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
		model.addAttribute("kw", kw);
		
		switch(type) {
		   case "COMCASE":
			  Page<ComCase> comList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", comList);
		      break;
		   case "CPU":
			   Page<CPU> cpuList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", cpuList);
		      break;
		   case "DISK":
			   Page<Disk> diskList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", diskList);
		      break;
		   case "GPU":
			   Page<GPU> gpuList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", gpuList);
		      break;
		   case "MBOARD":
			   Page<MBoard> mBoardList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", mBoardList);
		      break;
		   case "PSU":
			   Page<PSU> psuList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", psuList);
		      break;
		   case "RAM":
			   Page<RAM> ramList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", ramList);
		      break;
		   default:
			   Page<BaseProduct> baseList = this.pcPartService.get_List(page, kw, type, 10);
		      model.addAttribute("productList", baseList);
		      System.out.println(base.size());
		      break;
		}

		return "shop"; // 이동 
	}
	
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/goShop/{id}")
	public String goShop(Model model,@PathVariable("id") int id, Principal principal) {
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
		
		 if (principal != null) {
			 String username = SecurityContextHolder.getContext().getAuthentication().getName();
			 SiteUser user = userRepository.findByUserName(username).orElseThrow();
		        model.addAttribute("user", user);
		    }
		return "shop_fragment";
	}
	
	@GetMapping("/filter")
	public String filter(Model model, @PathVariable("search") String type) {
		System.out.println("하는게 하는게 아니야");
		
		
		return "shop";
	}
	@GetMapping("completed")
	public String getMethodName() {
		return "buyCompleted";
	}
	
}
