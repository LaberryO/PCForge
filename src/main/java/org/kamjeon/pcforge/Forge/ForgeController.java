package org.kamjeon.pcforge.Forge;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kamjeon.pcforge.PCpart.PCpartService;
import org.kamjeon.pcforge.PCpart.PCpartUtils;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/forge")
public class ForgeController {
	private final ForgeService forgeService;
	// 필요하면 활성화 하기로
	// private final PCpartService pCpartService;

	// 상점에서 비교 요청
	@GetMapping("response/{part}")
	public String shopResponse(@PathVariable("part") String part, @RequestParam(value = "id") String id) {
		return "";
	}

	// 처음에 견적사이트 버튼 누르면
	@GetMapping("create")
	public String forge(Forge forge) {
		return "redirect:/forge/create/cpu";
	}

	@GetMapping("create/{status}")
	public String statusForge(@PathVariable("status") String status, Model model, Forge forge,
			@RequestParam(value = "kw", required = false) String kw,
			@RequestParam(value = "reset", required = false) String reset, HttpSession session, Principal principal,
			Authentication authentication, HttpServletRequest request) {
		try {

			String statusTemp = status.toLowerCase();
			String itemName = null;
			PCpartUtils.checkPCPart(statusTemp);
			List<?> forgeSearchList = null; // 변수 선언

			// 세션 안에 있는 모든 Forge 가져오기
			List<Forge> forgeList = this.forgeService.getForgesForSession(session);

			// 없으면 새로 하나 만들기
			if (forgeList.isEmpty()) {
				Forge forge2 = this.forgeService.createNewForgeForSession(session);
				forgeList.add(forge2);
			}

			// 일단 가장 최신 걸 쓴다
			Forge forgeCartList = forgeList.get(forgeList.size() - 1);

			// share가 null이 아니면 새로 Forge 객체를 만들고 리스트에 추가
			// forgeCartList가 null일 가능성도 있으니 null 체크
			if (forgeCartList != null && forgeCartList.getShare() != null) {
			    Forge forge3 = this.forgeService.createNewForgeForSession(session);
			    // 새로 생성된 forge 객체를 forgeList에 추가
			    forgeList.add(forge3);
			    // 새로 추가된 객체를 forgeCartList에 할당
			    forgeCartList = forgeList.get(forgeList.size() - 1);
			} else {
			    // forgeCartList나 getShare()가 null이면 새로운 리스트를 만들어서 처리
			    forgeCartList = new Forge();
			    forgeList.add(forgeCartList);
			}


			// kw가 비어있는지 체크
			boolean isKwEmpty = (kw == null || kw.trim().isEmpty());

			// 부품 상태에 따른 검색 목록을 설정
			switch (statusTemp) {
			case "cpu":
				itemName = forgeCartList.getCpu() != null ? forgeCartList.getCpu().getName() : null;
				forgeSearchList = isKwEmpty ? this.forgeService.getCPUList() : this.forgeService.getCPUList(kw);
				break;
			case "comcase":
				itemName = forgeCartList.getComCase() != null ? forgeCartList.getComCase().getName() : null;
				forgeSearchList = isKwEmpty ? this.forgeService.getComCaseList() : this.forgeService.getComCaseList(kw);
				break;
			case "disk":
				itemName = forgeCartList.getDisk() != null ? forgeCartList.getDisk().getName() : null;
				forgeSearchList = isKwEmpty ? this.forgeService.getDiskList() : this.forgeService.getDiskList(kw);
				break;
			case "gpu":
				itemName = forgeCartList.getGpu() != null ? forgeCartList.getGpu().getName() : null;
				forgeSearchList = isKwEmpty ? this.forgeService.getGPUList() : this.forgeService.getGPUList(kw);
				break;
			case "mboard":
				itemName = forgeCartList.getMboard() != null ? forgeCartList.getMboard().getName() : null;
				forgeSearchList = isKwEmpty ? this.forgeService.getMBoardList() : this.forgeService.getMBoardList(kw);
				break;
			case "psu":
				itemName = forgeCartList.getPsu() != null ? forgeCartList.getPsu().getName() : null;
				forgeSearchList = isKwEmpty ? this.forgeService.getPSUList() : this.forgeService.getPSUList(kw);
				break;
			case "ram":
				itemName = forgeCartList.getRam() != null ? forgeCartList.getRam().getName() : null;
				forgeSearchList = isKwEmpty ? this.forgeService.getRAMList() : this.forgeService.getRAMList(kw);
				break;
			default:
				throw new IllegalArgumentException("알 수 없는 부품입니다: " + statusTemp);
			}

			// 모델에 필요한 값 추가
			model.addAttribute("statusTemp", statusTemp);
			model.addAttribute("forgeSearchList", forgeSearchList);
			model.addAttribute("itemId", itemName);
			if (principal != null) {
				model.addAttribute("usernameTest", principal.getName());
			}
		} catch (IllegalArgumentException e) {
			System.out.println("에러 발생: " + e.getMessage());
		}
		return "forge";
	}

	@GetMapping("create/{status}/getThis")
	public ResponseEntity<Map<String, Object>> getThis(@PathVariable("status") String status,
			@RequestParam("id") Integer id) {
		String statusTemp = status.toLowerCase();
		PCpartUtils.checkPCPart(statusTemp);
		// 단일 검색
		Optional<?> forgeSearchOne = Optional.empty();

		switch (statusTemp) {
		case "cpu":
			forgeSearchOne = this.forgeService.getCpu(id);
			break;
		case "comcase":
			forgeSearchOne = this.forgeService.getComCase(id);
			break;
		case "disk":
			forgeSearchOne = this.forgeService.getDisk(id);
			break;
		case "gpu":
			forgeSearchOne = this.forgeService.getGPU(id);
			break;
		case "mboard":
			forgeSearchOne = this.forgeService.getMBoard(id);
			break;
		case "psu":
			forgeSearchOne = this.forgeService.getPSU(id);
			break;
		case "ram":
			forgeSearchOne = this.forgeService.getRAM(id);
			break;
		}

		Map<String, Object> response = new HashMap<>();
		response.put("data", forgeSearchOne.orElse(null)); // Optional 값 반환
		return ResponseEntity.ok(response);
	}

	// 장바구니
	@GetMapping("create/{status}/cart")
	public String setCart(@PathVariable("status") String status, @RequestParam("id") Integer id, HttpSession session) {
		String statusTemp = status.toLowerCase();

		PCpartUtils.checkPCPart(statusTemp);
		// 부품 추가 또는 업데이트
		this.forgeService.addOrUpdatePart(statusTemp, id, session);

		return "redirect:/forge/create/" + statusTemp;
	}

	@GetMapping("buy")
	public String buy(HttpSession session, Model model) {
	    // 세션에서 forge 객체를 가져오기
	    Forge forge = this.forgeService.getForgeForSession(session);

	    // share가 null인지 확인하고 null이 아닐 때만 처리
	    if (forge != null && forge.getShare() != null) {
	        // share가 존재하는 경우 처리 로직 추가
	        // 예를 들어, 공유된 forge 객체가 있다면 해당 객체를 리스트에 추가하는 등의 처리
	        model.addAttribute("share", forge.getShare());  // share 객체를 모델에 추가
	    } else {
	        // share가 null인 경우 처리할 로직 (예: 기본값 설정 등)
	        model.addAttribute("share", null);  // 기본값으로 null을 추가할 수도 있습니다.
	    }

	    // forge 객체를 모델에 추가
	    model.addAttribute("forgeList", forge);

	    return "forge_buy";
	}


	// 다음 페이지로 이동
	@GetMapping("next/{status}")
	public String nextPage(@PathVariable("status") String status) {
		String statusTemp = status.toLowerCase();
		int index = 0;

		PCpartUtils.checkPCPart(statusTemp);

		String[] pcparts = PCpartUtils.getPCparts();

		for (int i = 0; i < pcparts.length; i++) {
			if (pcparts[i].equals(statusTemp)) {
				if (i == pcparts.length - 1) {
					statusTemp = pcparts[i];
				} else {
					statusTemp = pcparts[i + 1];
				}
				break;
			}
		}

		return "redirect:/forge/create/" + statusTemp;
	}
}
