package org.kamjeon.pcforge;

import java.util.List;

import org.kamjeon.pcforge.Boards.StarterBoard;
import org.kamjeon.pcforge.Boards.StarterRepository;
import org.kamjeon.pcforge.Boards.StarterType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class PCForgeController {
	private final StarterRepository startRep;
	
	  @GetMapping("/")
	    public String newsroom(Model model) {
	        List<StarterBoard> computingNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.NEWS);
	        List<StarterBoard> gamingNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.COMPUTER);
	        List<StarterBoard> mobileNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.CAR);
	        List<StarterBoard> hardwareNews = startRep.findTop4ByTypeOrderByIdDesc(StarterType.AI);

	        model.addAttribute("newsGroups", List.of(computingNews, gamingNews, mobileNews, hardwareNews));
	        model.addAttribute("categories", List.of("NEWS", "COMPUTER", "CAR", "AI"));

	        return "index"; // Thymeleaf 파일 이름
	    }
}
