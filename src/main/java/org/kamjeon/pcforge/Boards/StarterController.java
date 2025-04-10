package org.kamjeon.pcforge.Boards;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("starter")
@RequiredArgsConstructor
@Controller
public class StarterController {
	private final StarterService starterService;
	private final StarterRepository starterBoardRepository;

	@GetMapping("/main")
	public String main(@RequestParam(value = "kw", defaultValue = "") String kw,
			@RequestParam(value = "page", defaultValue = "0") int page, Model model) {
		
		System.out.println("페이지 번호" + page);
		List<StarterBoard> newsList = starterBoardRepository.findAll();
		List<List<StarterBoard>> checkNewsList = starterService.partitionList(newsList, 4);
		Page<StarterBoard> newsPage = starterService.getList(page, kw);

		model.addAttribute("allList", newsList);
		model.addAttribute("newsList", checkNewsList);

		model.addAttribute("paging", newsPage);
		model.addAttribute("kw", kw);

		return "starter";
	}

	@GetMapping("/create")
	public String create(StarterForm starter, Model model) {
		model.addAttribute("types", StarterType.values());
		return "starter_form";
	}
	
	@GetMapping("/delete")
	public String delete(Model model) {
		List<StarterBoard> newsList = starterBoardRepository.findAll();
		model.addAttribute("starterBoards", newsList);
		
		return "starter_delete";
	}
	
	@PreAuthorize("isAuthenticated()")
	@PostMapping("/delete/{id}")
	public String boardDelete(Model model, @PathVariable("id") Integer id) {
		
		Optional<StarterBoard> board = this.starterBoardRepository.findById(id);
		StarterBoard starter = board.get();
		
		this.starterBoardRepository.delete(starter);
		
		List<StarterBoard> boardList = this.starterBoardRepository.findAll();
		model.addAttribute("starterBoards", boardList);
		
		return "starter_delete";
	}
	

	@PostMapping("/create")
	public String create(@Valid StarterForm starter, BindingResult bindingResult, Model model) {
		MultipartFile image = starter.getImage();

		if (image == null || image.isEmpty()) {
			bindingResult.rejectValue("image", "error.image", "이미지를 업로드해야 합니다.");
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("types", StarterType.values());
			return "starter_form";
		}

		String imageName = null;
		try {
			imageName = starterService.saveIamge(image);
		} catch (IOException e) {
			bindingResult.rejectValue("image", "error.image", "이미지 저장 실패");
			return "starter_form";
		}

		this.starterService.create(starter.getTitle(), imageName, starter.getContent(), starter.getType());
		return "redirect:/";
	}

	@GetMapping(value = "/news/{id}")
	public String goNews(Model model, @PathVariable("id") Integer id) {

		StarterBoard board = this.starterService.getStarterBoard(id);
		model.addAttribute("starterBoard", board);

		return "starter_shop";
	}

}
