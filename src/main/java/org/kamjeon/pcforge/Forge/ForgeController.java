package org.kamjeon.pcforge.Forge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.kamjeon.pcforge.PCpart.PCpartService;
import org.kamjeon.pcforge.PCpart.PCpartUtils;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/forge")
public class ForgeController {
	private final ForgeService forgeService;
	// 필요하면 활성화 하기로
	//private final PCpartService pCpartService;

	// 처음에 견적사이트 버튼 누르면
	@GetMapping("create")
	public String forge(Forge forge) {
		return "redirect:/forge/create/cpu";
	}

	@GetMapping("create/{status}")
	public String statusForge(@PathVariable("status") String status, Model model, Forge forge,
			@RequestParam(value = "kw", required = false) String kw) {
		try {
			String statusTemp = status.toLowerCase();
			PCpartUtils.checkPCPart(statusTemp);
			List<?> forgeSearchList = null; // 변수 선언

			boolean isKwEmpty = (kw == null || kw.trim().isEmpty());

			switch (statusTemp) {
			case "cpu":
				forgeSearchList = isKwEmpty ? this.forgeService.getCPUList() : this.forgeService.getCPUList(kw);
				break;
			case "comcase":
				forgeSearchList = isKwEmpty ? this.forgeService.getComCaseList()
						: this.forgeService.getComCaseList(kw);
				break;
			case "disk":
				forgeSearchList = isKwEmpty ? this.forgeService.getDiskList() : this.forgeService.getDiskList(kw);
				break;
			case "gpu":
				forgeSearchList = isKwEmpty ? this.forgeService.getGPUList() : this.forgeService.getGPUList(kw);
				break;
			case "mboard":
				forgeSearchList = isKwEmpty ? this.forgeService.getMBoardList() : this.forgeService.getMBoardList(kw);
				break;
			case "psu":
				forgeSearchList = isKwEmpty ? this.forgeService.getPSUList() : this.forgeService.getPSUList(kw);
				break;
			case "ram":
				forgeSearchList = isKwEmpty ? this.forgeService.getRAMList() : this.forgeService.getRAMList(kw);
				break;
			default:
				throw new IllegalArgumentException("알 수 없는 부품입니다: " + statusTemp);
			}
			model.addAttribute("statusTemp", statusTemp);
			model.addAttribute("forgeSearchList", forgeSearchList);
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

}
