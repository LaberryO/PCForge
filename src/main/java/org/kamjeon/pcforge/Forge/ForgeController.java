package org.kamjeon.pcforge.Forge;

import java.util.List;

import org.kamjeon.pcforge.PCpart.PCpartService;
import org.kamjeon.pcforge.PCpart.PCpartUtils;
import org.kamjeon.pcforge.PCpart.CPU.CPU;
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
	private final PCpartService pCpartService;

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
				forgeSearchList = isKwEmpty ? this.pCpartService.getCPUList() : this.pCpartService.getCPUList(kw);
				break;
			case "comcase":
				forgeSearchList = isKwEmpty ? this.pCpartService.getComCaseList()
						: this.pCpartService.getComCaseList(kw);
				break;
			case "disk":
				forgeSearchList = isKwEmpty ? this.pCpartService.getDiskList() : this.pCpartService.getDiskList(kw);
				break;
			case "gpu":
				forgeSearchList = isKwEmpty ? this.pCpartService.getGPUList() : this.pCpartService.getGPUList(kw);
				break;
			case "mboard":
				forgeSearchList = isKwEmpty ? this.pCpartService.getMBoardList() : this.pCpartService.getMBoardList(kw);
				break;
			case "psu":
				forgeSearchList = isKwEmpty ? this.pCpartService.getPSUList() : this.pCpartService.getPSUList(kw);
				break;
			case "ram":
				forgeSearchList = isKwEmpty ? this.pCpartService.getRAMList() : this.pCpartService.getRAMList(kw);
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

}
