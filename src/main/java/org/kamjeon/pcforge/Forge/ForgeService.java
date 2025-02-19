package org.kamjeon.pcforge.Forge;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ForgeService {
	private final ForgeRepository forgeRepository;
	
	
	//극 초반 간단하게 forge만 생성
	public Forge create(Model model) {
		Forge forge = new Forge();
		model.addAttribute("forge", forge);
		return forge;
	}
}
