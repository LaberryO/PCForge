package org.kamjeon.pcforge;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.kamjeon.pcforge.Boards.StarterBoard;
import org.kamjeon.pcforge.Boards.StarterRepository;
import org.kamjeon.pcforge.Boards.StarterType;
import org.kamjeon.pcforge.PCpart.BaseProduct;
import org.kamjeon.pcforge.PCpart.PCParts;
import org.kamjeon.pcforge.PCpart.PCpartRepository;
import org.kamjeon.pcforge.PCpart.PCpartService;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PCForgeController {
	private final StarterRepository startRep;
	private final PCpartService pcPartService;
	private final PCpartRepository pcPartsRepository;
	
	  @GetMapping("/")
	    public String newsroom(Model model) {
	        List<StarterBoard> computingNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.NEWS);
	        List<StarterBoard> gamingNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.COMPUTER);
	        List<StarterBoard> mobileNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.CAR);
	        List<StarterBoard> hardwareNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.AI);

	        model.addAttribute("newsGroups", List.of(computingNews, gamingNews, mobileNews, hardwareNews));
	        model.addAttribute("categories", List.of("NEWS", "COMPUTER", "CAR", "AI"));
	     // ------------------------------------ 상점 초기 부분   
	        
	   	 List<BaseProduct> paging = this.pcPartService.getSearchList(0, "", "ALL" , 27);
	   	 
	 
	   	
		 model.addAttribute("products",paging);
		 
	        return "index"; // Thymeleaf 파일 이름
	    }
	
		
}
