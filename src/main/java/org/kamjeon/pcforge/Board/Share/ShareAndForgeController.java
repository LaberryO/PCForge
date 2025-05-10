package org.kamjeon.pcforge.Board.Share;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("forge/{id}/share")
@Controller
public class ShareAndForgeController {
	
	//처음 구매하기에서 게시글 작성하기로 이동하는
	/*
	 * @PreAuthorize("isAuthenticated()")
	 * 
	 * @GetMapping("/create") public String write(Model model, @PathVariable("id")
	 * Integer id, ShareForm shareForm) { Optional<Forge> forge =
	 * this.forgeService.getForge(id); if(forge.isPresent()) {
	 * model.addAttribute("forge", forge); } return ""; // 글 작성하는 페이지로 이동 #kim }
	 * 
	 * @PreAuthorize("isAuthenticated()")
	 * 
	 * @PostMapping("/create") public String write(@Valid ShareForm share,
	 * BindingResult bind, @PathVariable("id") Integer id, Principal principal) {
	 * if(bind.hasErrors()) return ""; // 원래 자기 페이지로 이동 #kim
	 * 
	 * Optional<Forge> opForge = this.forgeService.getForge(id);
	 * 
	 * if (opForge.isEmpty()) throw new
	 * NoSuchElementException("해당 ID의 Forge를 찾을 수 없습니다. ID: " + id);
	 * 
	 * Forge forge = opForge.get(); SiteUser user =
	 * this.userService.getUser(principal.getName()); this.shareService.save(forge,
	 * share.getSubject(), share.getContent(), user);
	 * 
	 * return ""; // 성공시 리스트 html로 이동 #kim }
	 */
}
